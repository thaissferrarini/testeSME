import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { Usuario } from 'src/app/models/usuario.model';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable, debounceTime, distinctUntilChanged, of, switchMap } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css']
})
export class UsuarioListComponent implements OnInit {
  filtroNome: string = '';
  filtroCPF: string = '';
  filtroSexo: string = '';
  usuarios: Usuario[] = [];
  usuariosFiltrados: Usuario[];
  public formModel: FormGroup;
  public formPesquisaUsuario: FormGroup;
  sexoOptions = [ 
    { label: 'Todos', value: ''},
    { label: 'Feminino', value: 'Feminino' },
    { label: 'Masculino', value: 'Masculino' }
  ];
  mostrarDetalhes: boolean = false;
  detalhe: Usuario;
  exibirDialogoConfirmacao: boolean = false;
  usuarioParaExcluir: any;

  constructor(
    private fb: FormBuilder,
    private cdr: ChangeDetectorRef,
    private usuarioService: UsuarioService,
    private router: Router, 
    private route: ActivatedRoute,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.formPesquisaUsuario = this.fb.group({
      nome: [''], 
      cpf: [''], 
      sexo: ['']
    });
    this.cdr.detectChanges();
    this.buscar();
  }

  formatarCpf(cpf: string): string {
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
  }

  buscar(): void {
    this.usuarioService.listarUsuarios().subscribe((usuarios: any[]) => {
      this.usuarios = usuarios;
      this.usuariosFiltrados = usuarios;
    });
  }

  filtrar() {
    const termoNome = this.formPesquisaUsuario.get('nome').value.toLowerCase();
    const termoCPF = this.formPesquisaUsuario.get('cpf').value.toLowerCase();
    const termoSexo = this.formPesquisaUsuario.get('sexo').value.toLowerCase();

    this.usuariosFiltrados = this.usuarios.filter(usuario => {
      const nomeMatch = usuario.nome.toLowerCase().includes(termoNome);
      const cpfMatch = usuario.cpf.toLowerCase().includes(termoCPF);
      const sexoMatch = usuario.sexo.toString().toLowerCase().includes(termoSexo);

      return nomeMatch && cpfMatch && sexoMatch;
    });
  }

  abrirDetalhes(row) {
    this.mostrarDetalhes = !this.mostrarDetalhes;

    this.detalhe = row;

  }

  editar(usuario: Usuario) {           
    this.router.navigate(['/editar'], { queryParams: { id: usuario.id } });
  }
  
  confirmarExclusao(usuario: any): void {
    this.usuarioParaExcluir = usuario;
    this.exibirDialogoConfirmacao = true;
  }

  cancelarExclusao(): void {
    this.exibirDialogoConfirmacao = false;
    this.alertService.info('Exclusão cancelada!');
  }

  excluir(): void {
    if (this.usuarioParaExcluir) {
      this.usuarioService.excluirUsuario(this.usuarioParaExcluir.id)
        .subscribe(
          () => {
            this.alertService.sucesso(`Usuário excluído com sucesso!`);
            this.buscar();
          },
          (error) => {
            this.alertService.erro('Erro inesperado no sistema, tente mais tarde!');
          }
        );
    }
    this.exibirDialogoConfirmacao = false;
  }

}