package com.example.api_ponto_de_venda.service;

import com.example.api_ponto_de_venda.model.Cliente;
import com.example.api_ponto_de_venda.model.Usuario;
import com.example.api_ponto_de_venda.repository.ClienteRepository;
import com.example.api_ponto_de_venda.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final ClienteRepository clienteRepository;
  private final PasswordEncoder passwordEncoder;

  public UsuarioService(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository) {
    this.usuarioRepository = usuarioRepository;
    this.clienteRepository = clienteRepository;
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

    Cliente novoCliete = new Cliente();
    novoCliete.setName(name);
    novoCliete.setEmail(email);
    novoCliete.setSenha(senhaCriptografada);
    return clienteRepository.save(novoCliete);
  }

  public Optional<Usuario> buscarPorEmail(String email){
    return usuarioRepository.findByEmail(email);
  }
}
