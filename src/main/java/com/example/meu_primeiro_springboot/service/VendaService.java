package com.example.meu_primeiro_springboot.service;


import com.example.meu_primeiro_springboot.dto.VendaRequestDto;

import com.example.meu_primeiro_springboot.dto.VendaResponseDto;

import com.example.meu_primeiro_springboot.model.*;

import com.example.meu_primeiro_springboot.repository.ProdutoRepository;

import com.example.meu_primeiro_springboot.repository.UsuarioRepository;

import com.example.meu_primeiro_springboot.repository.VendaRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;


import java.time.LocalDate;

import java.util.List;

import java.util.stream.Collectors;


@Service

public class VendaService {

  private final VendaRepository vendaRepository;

  private final ProdutoRepository produtoRepository;

  private final UsuarioRepository usuarioRepository;


  public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {

    this.vendaRepository = vendaRepository;

    this.produtoRepository = produtoRepository;

    this.usuarioRepository = usuarioRepository;

  }


  public List<Venda> listarVendas(){
    return vendaRepository.findAll();
  }


  @Transactional

  public VendaResponseDto adicionarVenda(VendaRequestDto dto, String emailUsuario) {

    // 1. Busca o usuário que está logado

    Usuario usuario = usuarioRepository.findByEmail(emailUsuario)

        .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + emailUsuario));


    Venda novaVenda = new Venda();

    novaVenda.setUsuario(usuario);

    novaVenda.setData(LocalDate.now());

    novaVenda.setFormaPagamento(dto.formaPagamento());


    // 2. Mapeia os itens do DTO para a entidade, buscando cada produto

    List<ItemVenda> itens = dto.itens().stream().map(itemDto -> {


      // =====> AQUI ESTÁ A LÓGICA QUE EVITA O ERRO <=====

      Produto produto = produtoRepository.findById(itemDto.produtoId())

          .orElseThrow(() -> new RuntimeException("Produto com ID " + itemDto.produtoId() + " não encontrado."));


      if (produto.getQuantEstoque() < itemDto.quantidade()) {

        throw new RuntimeException("Estoque insuficiente para o produto " + produto.getName());

      }


      produto.setQuantEstoque(produto.getQuantEstoque() - itemDto.quantidade());

      // O save do produto é gerenciado pela transação do JPA


      ItemVenda itemVenda = new ItemVenda();

      itemVenda.setProduto(produto);

      itemVenda.setQuantidade((long) itemDto.quantidade());

      itemVenda.setVenda(novaVenda);

      return itemVenda;

    }).collect(Collectors.toList());


    novaVenda.setItens(itens);

    novaVenda.calcularValorTotal(); // Calcula o valor total com base nos itens


    Venda vendaSalva = vendaRepository.save(novaVenda);


    // 3. Converte a entidade salva para um DTO de resposta

    return new VendaResponseDto(

        vendaSalva.getId(),

        vendaSalva.getData(),

        vendaSalva.getValorTotal(),

        vendaSalva.getFormaPagamento(),

        vendaSalva.getUsuario().getName()

    );

  }

}