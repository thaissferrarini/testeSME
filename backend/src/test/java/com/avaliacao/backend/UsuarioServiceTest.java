package com.avaliacao.backend;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.avaliacao.backend.dto.UsuarioDto;
import com.avaliacao.backend.enums.SexoEnum;
import com.avaliacao.backend.model.Usuario;
import com.avaliacao.backend.repository.UsuarioRepository;
import com.avaliacao.backend.service.UsuarioServiceImpl;

public class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private UsuarioServiceImpl usuarioService;

    private static final String CPF_EXISTING = "123.456.789-00";
    private static final String CPF_NEW = "987.654.321-00";

    @Before
    public void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        usuarioService = new UsuarioServiceImpl(usuarioRepository);

        mockGetUsuarioId1();
        mockSaveUsuarioId2();
    }

    private void mockSaveUsuarioId2() {
        Usuario usuario = buildUsuario(2L, "Maria", CPF_NEW);
        when(usuarioRepository.save(any())).thenReturn(usuario);
    }

    private void mockGetUsuarioId1() {
        Usuario usuario = buildUsuario(1L, "null", CPF_EXISTING);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
    }

    @Test
    public void testSave() {
        UsuarioDto usuarioDto = buildUsuarioDto(null, "João da Silva", CPF_NEW);
        when(usuarioRepository.findByCpf(anyString())).thenReturn(null);

        UsuarioDto savedUsuarioDto = usuarioService.save(usuarioDto);

        assertNotNull(savedUsuarioDto);
    }

    @Test
    public void testFindAll() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(buildUsuario(1L, "Maria", CPF_EXISTING));
        usuarios.add(buildUsuario(2L, "João", CPF_NEW));
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<UsuarioDto> usuariosDto = usuarioService.findAll();

        assertNotNull(usuariosDto);
        assertEquals(2, usuariosDto.size());
    }

    @Test
    public void testFindAllEmpty() {
        when(usuarioRepository.findAll()).thenReturn(new ArrayList<>());

        List<UsuarioDto> usuariosDto = usuarioService.findAll();

        assertNotNull(usuariosDto);
        assertTrue(usuariosDto.isEmpty());
    }

    @Test
    public void testFindById() {
        Long id = 1L;

        UsuarioDto usuarioDto = usuarioService.findById(id);

        assertNotNull(usuarioDto);
        assertEquals(id, usuarioDto.getId());
    }

    @Test
    public void testUpdate() {
        UsuarioDto usuarioDto = buildUsuarioDto(null, "João da Silva", CPF_NEW);
        Usuario usuario = buildUsuario(1L, "Maria", CPF_EXISTING);
        when(usuarioRepository.findByCpf(anyString())).thenReturn(null);
        when(usuarioRepository.findById(usuarioDto.getId())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDto updatedUsuarioDto = usuarioService.update(usuarioDto);

        assertNotNull(updatedUsuarioDto);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;

        usuarioService.deleteById(id);

        verify(usuarioRepository).deleteById(id);
    }

    private UsuarioDto buildUsuarioDto(Long id, String nome, String cpf) {
        return new UsuarioDto(id, nome, cpf, SexoEnum.MASCULINO, new Date(), null, null);
    }

    private Usuario buildUsuario(Long id, String nome, String cpf) {
        return Usuario.builder()
                .id(id)
                .nome(nome)
                .cpf(cpf)
                .dataNascimento(new Date())
                .build();
    }
}