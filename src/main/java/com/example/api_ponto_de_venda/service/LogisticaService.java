package com.example.api_ponto_de_venda.service;

import com.example.api_ponto_de_venda.exceptions.RecursoNaoEncontradoException;
import com.example.api_ponto_de_venda.model.Logistica;
import com.example.api_ponto_de_venda.model.Venda;
import com.example.api_ponto_de_venda.repository.LogisticaRepository;
import com.example.api_ponto_de_venda.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogisticaService {

    private final LogisticaRepository logisticaRepository;
    private final VendaRepository vendaRepository;

    public LogisticaService(LogisticaRepository logisticaRepository, VendaRepository vendaRepository){
        this.logisticaRepository = logisticaRepository;
        this.vendaRepository = vendaRepository;
    }

    @Transactional
    public Logistica adicionarDetalhesDeEnvio(Long vendaId, Logistica detalhesLogistica){
        Venda venda = vendaRepository.findById(vendaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Venda com ID: " + vendaId +" não encontrado"));

        Logistica logistica = new Logistica();
        logistica.setVenda(venda);
        logistica.setCodigoDeRastreio(detalhesLogistica.getCodigoDeRastreio());
        logistica.setEnderecoDeEntrega(detalhesLogistica.getEnderecoDeEntrega());
        logistica.setStatus("ENVIADO");

        return logisticaRepository.save(logistica);
    }
}
