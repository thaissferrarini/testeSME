package com.avaliacao.backend.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.avaliacao.backend.enums.SexoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
  private Long id;

  @NotBlank
  private String nome;

  @NotBlank
  private String cpf;

  private SexoEnum sexo;

  @Past
  private LocalDate dataNascimento;

  private String pai;

  private String mae;
}
