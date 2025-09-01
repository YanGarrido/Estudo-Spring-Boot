package com.example.api_ponto_de_venda.controller;

import com.example.api_ponto_de_venda.model.Fornecedor;
import com.example.api_ponto_de_venda.service.FornecedorService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService){
        this.fornecedorService = fornecedorService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Fornecedor> registrarFornecedor(@RequestBody Fornecedor fornecedor){
        Fornecedor novoFornecedor = fornecedorService.registrarFornecedor(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFornecedor);
    }
}
