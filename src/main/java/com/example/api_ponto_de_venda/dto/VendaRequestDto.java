package com.example.api_ponto_de_venda.dto;

import java.util.List;

public record VendaRequestDto(String formaPagamento, List<ItemVendaDto> itens) {
}
