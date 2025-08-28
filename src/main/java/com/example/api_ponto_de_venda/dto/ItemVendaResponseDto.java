package com.example.api_ponto_de_venda.dto;

public record ItemVendaResponseDto(
    Long id,
    String nomeProduto,
    Long quantidade,
    double precoUnitario
) {}
