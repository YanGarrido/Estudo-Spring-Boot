package com.example.meu_primeiro_springboot.dto;

import java.time.LocalDate;

public record VendaResponseDto(
    Long id,
    LocalDate data,
    Double valorTotal,
    String formaPagamento,
    String nomeCliente
) {
}
