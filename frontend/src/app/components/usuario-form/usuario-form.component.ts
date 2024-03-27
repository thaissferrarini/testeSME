import { Component, Input, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService, SelectItem } from 'primeng/api';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from 'src/app/models/usuario.model';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements OnInit {
  @Input() usuario: Usuario;
  public formModel: FormGroup;
  public formPesquisaUsuario: FormGroup;
  titulo: string = 'Cadastro de Usuário';
  contatos = { nome: '', email: '', telefone: '' };
  cadastroContatos = [];
  mostrarCadastro = true;
  maxDate = new Date();
  sexoOptions: SelectItem[] = [
    { label: 'MASCULINO', value: 'MASCULINO' },
    { label: 'FEMININO', value: 'FEMININO' }
  ];

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private messageService: MessageService,
    private router: Router, 
    private route: ActivatedRoute,
    private alertService: AlertService
  ) { }

  ngOnInit() {
    this.createForm();
    this.route.queryParams.subscribe(params => {
      const userId = params['id'];
        this.verificarUsuarios(userId);
        this.buscar(userId);
      });
      
  }

  buscar(id): void {
    this.usuarioService.buscarUsuario(id).subscribe((usuario: Usuario) => {
          if(!usuario) {
            this.alertService.erro(`Usuário id: ${id} não encontrado`); 
            return;
          }
          this.formModel = this.fb.group({
          id: usuario.id,
          nome: usuario.nome,
          cpf: usuario.cpf,
          sexo: usuario.sexo,
          dataNascimento: new Date(usuario.dataNascimento),
          mae: usuario.mae,
          pai: usuario.pai,
        });
    });
  }

  verificarUsuarios(id): void {
    if (id != null) {
      this.titulo = 'Editar Usuário';
    } else {
      this.titulo = 'Cadastro de Usuário';
    }
  }
  
  private createForm() {
    this.formModel = this.fb.group({
      nome: ['', Validators.required],
      cpf: ['', Validators.required],
      sexo: ['', Validators.required],
      dataNascimento: ['', Validators.required],
      pai: [''],
      mae: ['']
    });

    this.formPesquisaUsuario = this.fb.group({
      id: null,
    });
  }

  salvar() {
    if (this.formModel.valid) {
      this.formModel.value.cpf = this.formModel.value.cpf.replace(/\D/g, '');
      if(this.formModel.value.id == null) {
        this.usuarioService.adicionarUsuario(this.formModel.value).subscribe(
          (data: any) => {
            this.alertService.sucesso('Usuário cadastrado com sucesso!')
            this.router.navigate(['']);
          },
          (error) => {
            this.alertService.erro(error.error)
          },
          () => {}
        )
      } else {
        this.usuarioService.atualizarUsuario(this.formModel.value).subscribe(
          (data: any) => {
            this.alertService.sucesso('Usuário editado com sucesso!')
            this.router.navigate(['']);
          },
          (error) => {
            this.alertService.erro(error.error)
          },
          () => {}
        )
      }
    } else {
      this.alertService.aviso('Preencha todos os campos obrigatórios');
    }

    
  }

  adicionarContato() {
    this.cadastroContatos.push({
      nome: this.contatos.nome,
      email: this.contatos.email,
      telefone: this.contatos.telefone,
    })

    this.contatos.nome = null;
    this.contatos.email = null;
    this.contatos.telefone = null;
  }
}
