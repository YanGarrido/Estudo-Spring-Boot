package com.example.meu_primeiro_springboot.dto;

public record ItemVendaResponseDto(
    Long id,
    String nomeProduto,
    Long quantidade,
    double precoUnitario
) {}
