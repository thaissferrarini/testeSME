package com.avaliacao.backend.repository;

import com.avaliacao.backend.enums.SexoEnum;
import com.avaliacao.backend.model.Usuario;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

  public List<Usuario> findByNomeAndSexo(String nome, SexoEnum sexo);

  public Usuario findByCpf(String cpf);

}
