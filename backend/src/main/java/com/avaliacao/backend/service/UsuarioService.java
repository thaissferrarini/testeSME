package com.avaliacao.backend.service;

import com.avaliacao.backend.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {

    UsuarioDto save(UsuarioDto usuario);

    List<UsuarioDto> findAll();

    UsuarioDto findById(Long id);

    UsuarioDto update(UsuarioDto usuario);

    void deleteById(Long id);

}
