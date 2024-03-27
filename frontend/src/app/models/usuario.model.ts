export interface Usuario {
  id?: number;
  nome: string;
  cpf: string;
  sexo: string;
  dataNascimento: Date;
  pai?: string;
  mae?: string;
}