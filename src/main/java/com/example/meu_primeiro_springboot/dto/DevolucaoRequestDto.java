package com.example.meu_primeiro_springboot.dto;

import com.example.meu_primeiro_springboot.model.ItemDevolucao;

import java.util.List;

public record DevolucaoRequestDto(Long vendaId, String motivo, List<ItemDevolucao> itensDevolvidos) {
}
