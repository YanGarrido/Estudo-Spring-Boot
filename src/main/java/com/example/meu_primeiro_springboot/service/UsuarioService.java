package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.model.Usuario;
import com.example.meu_primeiro_springboot.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

  UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }
  public List<Usuario> listarUsuarios(){
    return usuarioRepository.findAll();
  }
  public Usuario registrarUsuario(Usuario usuario){
    if (usuario.getName().isEmpty()){
      throw new IllegalArgumentException("Nome não pode ser vazio");
    }
    if (usuario.getEmail().isEmpty()){
      throw new IllegalArgumentException("Esse email não pode ser vazio");
    }
    if(usuario.getSenha().isEmpty()){
      throw new IllegalArgumentException("O campo senha não pode ser vazio");
    }
    if (usuarioRepository.existsByEmail(usuario.getEmail())){
      throw new RuntimeException("Email já registrado");
    }
    return usuarioRepository.save(usuario);
  }
}
