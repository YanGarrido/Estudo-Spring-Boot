package com.example.api_ponto_de_venda.dto;


import java.util.List;

public record DevolucaoRequestDto(Long vendaId, String motivo, List<ItemDevolucaoDto> itensDevolvidos) {
}
