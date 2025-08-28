package com.example.api_ponto_de_venda.service;

import com.example.api_ponto_de_venda.model.Usuario;
import com.example.api_ponto_de_venda.repository.UsuarioRepository;
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

    return new User(
        usuario.getEmail(),
        usuario.getSenha(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
    );
  }
}