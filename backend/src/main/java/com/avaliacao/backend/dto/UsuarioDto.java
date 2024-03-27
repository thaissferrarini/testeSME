package com.avaliacao.backend.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

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

  private Date dataNascimento;

  private String pai;

  private String mae;
}
