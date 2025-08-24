package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.model.Usuario;
import com.example.meu_primeiro_springboot.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }


  public Usuario registrarUsuario(String name, String email, String senha) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Nome não pode ser vazio");
    }
    if (email.isEmpty()) {
      throw new IllegalArgumentException("Esse email não pode ser vazio");
    }
    if (senha.isEmpty()) {
      throw new IllegalArgumentException("O campo senha não pode ser vazio");
    }
    if (usuarioRepository.existsByEmail(email)){
      throw new RuntimeException("Email já registrado");
    }
    String senhaCriptografada = passwordEncoder.encode(senha);
    Usuario usuario = new Usuario(name, email, senhaCriptografada);
    return usuarioRepository.save(usuario);
  }

  public Optional<Usuario> buscarPorEmail(String email){
    return usuarioRepository.findByEmail(email);
  }
}
