package com.avaliacao.backend.util;

import com.avaliacao.backend.dto.UsuarioDto;
import com.avaliacao.backend.model.Usuario;

public class EntityMapper {

    public static UsuarioDto usuarioDto(Usuario usuario) {

        if (usuario == null) {
            usuario = new Usuario();
        }
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setCpf(usuario.getCpf());
        usuarioDto.setSexo(usuario.getSexo());
        usuarioDto.setDataNascimento(usuario.getDataNascimento());
        usuarioDto.setMae(usuario.getMae() != null ? usuario.getMae().getNome() : null);
        usuarioDto.setPai(usuario.getPai() != null ? usuario.getPai().getNome() : null);

        return usuarioDto;
    }

    public static Usuario usuario(UsuarioDto usuarioDto, Usuario mae, Usuario pai) {

        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.getId());
        usuario.setNome(usuarioDto.getNome());
        usuario.setCpf(usuarioDto.getCpf());
        usuario.setSexo(usuarioDto.getSexo());
        usuario.setDataNascimento(usuarioDto.getDataNascimento());
        usuario.setMae(mae);
        usuario.setPai(pai);

        return usuario;
    }
}
