package com.avaliacao.backend.service;

import com.avaliacao.backend.dto.UsuarioDto;
import com.avaliacao.backend.enums.SexoEnum;
import com.avaliacao.backend.exception.ArgumentException;
import com.avaliacao.backend.model.Usuario;
import com.avaliacao.backend.repository.UsuarioRepository;
import com.avaliacao.backend.util.EntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    @Override
    public UsuarioDto save(UsuarioDto usuarioDto) {
        validarCPF(usuarioDto.getCpf());
        Usuario mae = findByNome(usuarioDto.getMae(), SexoEnum.FEMININO);
        Usuario pai = findByNome(usuarioDto.getPai(), SexoEnum.MASCULINO);
        Usuario usuario = usuarioRepository.save(EntityMapper.usuario(usuarioDto, mae, pai));

        return EntityMapper.usuarioDto(usuario);
    }

    @Override
    public List<UsuarioDto> findAll() {
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDto> usuariosDto = StreamSupport.stream(usuarios.spliterator(), false)
            .map(EntityMapper::usuarioDto).collect(Collectors.toList());
        return usuariosDto;
    }

    @Override
    public UsuarioDto findById(Long id) {
        return EntityMapper.usuarioDto(usuarioRepository.findById(id).get());
    }

    @Override
    public UsuarioDto update(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findById(usuarioDto.getId()).get();
        if(!usuario.getCpf().equals(usuarioDto.getCpf())) {
            validarCPF(usuarioDto.getCpf());
        }
        Usuario mae = findByNome(usuarioDto.getMae(), SexoEnum.FEMININO);
        Usuario pai = findByNome(usuarioDto.getPai(), SexoEnum.MASCULINO);
        usuario = EntityMapper.usuario(usuarioDto, mae, pai);
        return EntityMapper.usuarioDto(usuarioRepository.save(usuario));
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    private Usuario findByNome(String nome, SexoEnum sexo) {
        if (usuarioRepository.count() == 0 || ((nome == null || nome == "")))
            return null;

        List<Usuario> usuarios = usuarioRepository.findByNomeAndSexo(nome, sexo);

        if (usuarios.size() == 0) {
            if (sexo == SexoEnum.FEMININO) {
                throw new ArgumentException("Mãe não encontrada no sistema");
            } else {
                throw new ArgumentException("Pai não encontrado no sistema");
            }
        }

        if (usuarios.size() > 1)
            throw new ArgumentException(
                    "Existe mais de um usuário com este nome (mãe ou pai). Informe o nome completo");

        return usuarios.get(0);
    }

    private boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11)
            throw new ArgumentException("CPF inválido!");

        Usuario existingUser = usuarioRepository.findByCpf(cpf);
        
        if (existingUser != null )
        throw new ArgumentException("CPF já existe no sistema");

        return true;

    } 

}
