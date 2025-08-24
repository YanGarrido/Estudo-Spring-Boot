package com.example.meu_primeiro_springboot.controller;

import com.example.meu_primeiro_springboot.model.Usuario;
import com.example.meu_primeiro_springboot.security.JwtUtil;
import com.example.meu_primeiro_springboot.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UsuarioService usuarioService;

  public AuthController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> registrarUsuario(@RequestBody Map<String,String> request){
     Usuario usuario = usuarioService.registrarUsuario(request.get("name"), request.get("email"), "password");
     return ResponseEntity.ok(usuario);
  }

  @PostMapping("/login")
  public  ResponseEntity<?> login(@RequestBody Map<String, String> request){
    Optional<Usuario> usuario = usuarioService.buscarPorEmail(request.get("email"));
    if(usuario.isPresent() && usuario.get().getSenha().equals(request.get("senha"))){
      String token = JwtUtil.generateToken(usuario.get().getEmail());
      return ResponseEntity.ok(Map.of("token",token));
    }
    return ResponseEntity.status(401).body("Credeciais inválidas");
  }
}
