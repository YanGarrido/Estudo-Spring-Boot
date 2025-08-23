package com.example.meu_primeiro_springboot.repository;

import com.example.meu_primeiro_springboot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  boolean existsByEmail(String email);

}
