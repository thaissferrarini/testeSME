package com.avaliacao.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaliacao.backend.enums.SexoEnum;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 200, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private SexoEnum sexo;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @ManyToOne
    private Usuario pai;

    @ManyToOne()
    private Usuario mae;

}
