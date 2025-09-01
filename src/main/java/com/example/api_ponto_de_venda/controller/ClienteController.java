package com.example.api_ponto_de_venda.controller;

import com.example.api_ponto_de_venda.model.Cliente;
import com.example.api_ponto_de_venda.service.ClienteService;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping("/me")
    public ResponseEntity<Cliente> getMeuPerfil(Authentication authentication){
        String emailLogado = authentication.getName();

        return clienteService.buscarClienteEmail(emailLogado).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/me")
    public ResponseEntity<Cliente> atualizarMeuPerfil(@RequestBody Cliente dadosCliente, Authentication authentication){
       String emailLogado = authentication.getName();
       Cliente clienteAtualizado = clienteService.atualizarCliente(emailLogado, dadosCliente);
       return ResponseEntity.ok(clienteAtualizado);
    }
}
