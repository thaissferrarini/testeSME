import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Usuario } from '../models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private baseUrl = environment.baseServer +'/users';

  constructor(private http: HttpClient) { }

  listarUsuarios(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  buscarUsuario(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(this.baseUrl+'/'+id);
  }
  
  adicionarUsuario(usuario: any): Observable<any> {
    return this.http.post(this.baseUrl, usuario);
  }

  atualizarUsuario(usuario: any): Observable<any> {
    return this.http.put(`${this.baseUrl}`, usuario);
  }

  excluirUsuario(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}