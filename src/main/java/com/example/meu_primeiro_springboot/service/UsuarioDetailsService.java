package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.model.Usuario;
import com.example.meu_primeiro_springboot.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioDetailsService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Usuario usuario = usuarioRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

    // =====> AQUI ESTÁ A CORREÇÃO <=====
    // Agora o usuário recebe a permissão "ROLE_USER"
    return new User(
        usuario.getEmail(),
        usuario.getSenha(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
    );
  }
}