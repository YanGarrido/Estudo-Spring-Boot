package com.example.api_ponto_de_venda.dto;

import java.time.LocalDate;
import java.util.List;

public record VendaResponseDto(
    Long id,
    LocalDate data,
    Double valorTotal,
    String formaPagamento,
    String nomeCliente,
    List<ItemVendaResponseDto> itens // Lista de DTOs de item
) {}
