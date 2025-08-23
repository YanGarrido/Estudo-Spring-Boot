package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.model.Usuario;
import com.example.meu_primeiro_springboot.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

  UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public List<Usuario> listarUsuarios() {
    return usuarioRepository.findAll();
  }

  public Usuario registrarUsuario(Usuario usuario) {
    if (usuario.getName().isEmpty()) {
      throw new IllegalArgumentException("Nome não pode ser vazio");
    }
    if (usuario.getEmail().isEmpty()) {
      throw new IllegalArgumentException("Esse email não pode ser vazio");
    }
    if (usuario.getSenha().isEmpty()) {
      throw new IllegalArgumentException("O campo senha não pode ser vazio");
    }
    if (usuarioRepository.existsByEmail(usuario.getEmail())) {
      throw new RuntimeException("Email já registrado");
    }
    return usuarioRepository.save(usuario);
  }

  public Usuario login(String email, String senha) {
    if(email.isEmpty()){
      throw new IllegalArgumentException("Email não pode ser vazio");
    }
    if(senha.isEmpty()){
      throw new IllegalArgumentException("Senha não pode ser vazia");
    }
    Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

    if (!Objects.equals(senha, usuario.getSenha())) {
      throw new RuntimeException("Senha invalida");
    }
    return usuario;
  }
}
