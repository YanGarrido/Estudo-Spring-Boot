package com.example.meu_primeiro_springboot.service;


import com.example.meu_primeiro_springboot.dto.DevolucaoRequestDto;
import com.example.meu_primeiro_springboot.dto.ItemDevolucaoDto;
import com.example.meu_primeiro_springboot.exceptions.RecursoNaoEncontradoException;
import com.example.meu_primeiro_springboot.model.Devolucao;
import com.example.meu_primeiro_springboot.model.ItemDevolucao;
import com.example.meu_primeiro_springboot.model.Produto;
import com.example.meu_primeiro_springboot.model.Venda;
import com.example.meu_primeiro_springboot.repository.DevolucaoRepository;
import com.example.meu_primeiro_springboot.repository.ProdutoRepository;
import com.example.meu_primeiro_springboot.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DevolucaoService {
  private final DevolucaoRepository devolucaoRepository;
  private final VendaRepository vendaRepository;
  private final ProdutoRepository produtoRepository;
  private final ProdutoService produtoService;

  public DevolucaoService(DevolucaoRepository devolucaoRepository, VendaRepository vendaRepository, ProdutoRepository produtoRepository, ProdutoService produtoService){
    this.devolucaoRepository = devolucaoRepository;
    this.vendaRepository = vendaRepository;
    this.produtoService = produtoService;
    this.produtoRepository = produtoRepository;
  }

  @Transactional
  public Devolucao criarDevolucao(DevolucaoRequestDto dto){
    if (dto.itensDevolvidos() == null || dto.itensDevolvidos().isEmpty()){
      throw new IllegalArgumentException("A lista de itens para devolução está vaziz");
    }

    Venda vendaOriginal = vendaRepository.findById(dto.vendaId())
        .orElseThrow(() -> new RecursoNaoEncontradoException("Venda com ID"+ dto.vendaId() +" não encontrada"));

    Devolucao novaDevolucao = new Devolucao();
    novaDevolucao.setVenda(vendaOriginal);
    novaDevolucao.setData(LocalDate.now());
    novaDevolucao.setMotivoDevolucao(dto.motivo());

    List<ItemDevolucao> itensParaDevolver = new ArrayList<>();
    for (ItemDevolucaoDto itemDto : dto.itensDevolvidos()){
      Produto produtoDevolvido = produtoRepository.findById(itemDto.produtoId().orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + itemDto.produtoId() +" não encontrado")));

      ItemDevolucao itemDevolucao = new ItemDevolucao();
      itemDevolucao.setProduto(produtoDevolvido);
      itemDevolucao.setQuantidade(itemDto.quantidade());
      itemDevolucao.setDevolucao(novaDevolucao);

      itensParaDevolver.add(itemDevolucao);

      produtoService.adicionarEstoque(itemDto.produtoId(), itemDto.quantidade());
    }

    novaDevolucao.setItensDevolvidos(itensParaDevolver);

    return  devolucaoRepository.save(novaDevolucao);
  }
  public List<Devolucao> listarTodasDevolucoes(){
    return devolucaoRepository.findAll();
  }
}
