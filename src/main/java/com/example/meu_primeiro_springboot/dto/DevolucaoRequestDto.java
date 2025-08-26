package com.example.meu_primeiro_springboot.dto;


import java.util.List;

public record DevolucaoRequestDto(Long vendaId, String motivo, List<ItemDevolucaoDto> itensDevolvidos) {
}
