package com.example.api_ponto_de_venda.controller;

import com.example.api_ponto_de_venda.model.Logistica;
import com.example.api_ponto_de_venda.service.LogisticaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logistica")
public class LogisticaController {

    private final LogisticaService logisticaService;

    public LogisticaController(LogisticaService logisticaService){
        this.logisticaService = logisticaService;
    }

    @PostMapping("/venda/{vendaId}")
    public ResponseEntity<Logistica> adicionarEnvio(@PathVariable Long vendaId, @RequestBody Logistica detalhesLogistica){
        Logistica novoLogistica = logisticaService.adicionarDetalhesDeEnvio(vendaId, detalhesLogistica);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLogistica);
    }
}

