package com.example.api_ponto_de_venda.repository;

import com.example.api_ponto_de_venda.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  boolean existsByEmail(String email);

  Optional<Usuario> findByEmail(String email);

}
