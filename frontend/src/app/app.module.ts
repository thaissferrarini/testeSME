import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { AccordionModule } from 'primeng/accordion';
import { DialogModule } from 'primeng/dialog';
import { CalendarModule } from 'primeng/calendar';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService } from 'primeng/api';
import { MessagesModule } from 'primeng/messages';
import { HttpClientModule } from '@angular/common/http';
import { PaginatorModule } from 'primeng/paginator';
import { ToastModule } from 'primeng/toast';
import { UsuarioListComponent } from './components/usuario-list/usuario-list.component';
import { UsuarioService } from './services/usuario.service';
import { UsuarioFormComponent } from './components/usuario-form/usuario-form.component';
import { DropdownModule } from 'primeng/dropdown';
import { AlertService } from './services/alert.service';
import { ToastrModule } from 'ngx-toastr';
import { InputMaskModule } from 'primeng/inputmask'


const primeNg = [
  TableModule,
  ButtonModule,
  InputTextModule,
  AccordionModule,
  DialogModule,
  CalendarModule,
  FormsModule,
  ReactiveFormsModule,
  ConfirmDialogModule,
  MessagesModule,
  HttpClientModule,
  PaginatorModule,
  ToastModule,
  DropdownModule,
  AccordionModule,
  InputMaskModule

]


@NgModule({
  declarations: [
    UsuarioListComponent,
    UsuarioFormComponent,
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    primeNg,
    ToastrModule.forRoot(),
  ],
  providers: [ConfirmationService, MessageService, UsuarioService, AlertService],
  bootstrap: [AppComponent]
})
export class AppModule { }