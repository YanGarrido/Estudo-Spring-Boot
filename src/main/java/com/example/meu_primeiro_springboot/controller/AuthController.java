package com.example.meu_primeiro_springboot.controller;

import com.example.meu_primeiro_springboot.model.Usuario;
import com.example.meu_primeiro_springboot.security.JwtUtil;
import com.example.meu_primeiro_springboot.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UsuarioService usuarioService;
  private final AuthenticationManager authenticationManager;

  public AuthController(UsuarioService usuarioService, AuthenticationManager authenticationManager) {
    this.usuarioService = usuarioService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/register")
  public ResponseEntity<?> registrarUsuario(@RequestBody Map<String, String> request) {

    Usuario usuario = usuarioService.registrarUsuario(request.get("name"), request.get("email"), request.get("senha"));
    return ResponseEntity.ok(usuario);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.get("email"), request.get("senha"))
      );

      String email = authentication.getName();
      String token = JwtUtil.generateToken(email);

      return ResponseEntity.ok(Map.of("token", token));

    } catch (Exception e) {
      return ResponseEntity.status(401).body("Credenciais inválidas");
    }
  }
}