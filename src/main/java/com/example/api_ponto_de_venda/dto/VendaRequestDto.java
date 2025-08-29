package com.example.api_ponto_de_venda.dto;

import com.example.api_ponto_de_venda.model.Pagamento;

import java.util.List;

public record VendaRequestDto(String metodoPagamento, List<ItemVendaDto> itens) {
}
