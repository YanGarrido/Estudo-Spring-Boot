package com.example.meu_primeiro_springboot.controller;


import com.example.meu_primeiro_springboot.model.Usuario;
import com.example.meu_primeiro_springboot.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService){
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public List<Usuario> listarUsuarios(){
    return usuarioService.listarUsuarios();
  }

  @PostMapping
  public Usuario registrarUsuario(@RequestBody Usuario usuario){
    return usuarioService.registrarUsuario(usuario);
  }
}
