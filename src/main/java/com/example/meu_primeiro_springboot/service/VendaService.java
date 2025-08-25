package com.example.meu_primeiro_springboot.service;


import com.example.meu_primeiro_springboot.dto.ItemVendaResponseDto;
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


  public List<VendaResponseDto> listarVendas(){
    List<Venda> vendas = vendaRepository.findAll();

    return vendas.stream().map(venda -> {
      List<ItemVendaResponseDto> itensDto = venda.getItens().stream()
          .map(item -> new ItemVendaResponseDto(
              item.getId(),
              item.getProduto().getName(),
              item.getQuantidade(),
              item.getProduto().getPreco()
          )).collect(Collectors.toList());

      return new VendaResponseDto(
          venda.getId(),
          venda.getData(),
          venda.getValorTotal(),
          venda.getFormaPagamento(),
          venda.getUsuario().getName(),
          itensDto
      );
    }).collect(Collectors.toList());
  }


  @Transactional
  public VendaResponseDto adicionarVenda(VendaRequestDto dto, String emailUsuario) {
    Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + emailUsuario));

    Venda novaVenda = new Venda();
    novaVenda.setUsuario(usuario);
    novaVenda.setData(LocalDate.now());
    novaVenda.setFormaPagamento(dto.formaPagamento());

    List<ItemVenda> itens = dto.itens().stream().map(itemDto -> {

      Produto produto = produtoRepository.findById(itemDto.produtoId())
          .orElseThrow(() -> new RuntimeException("Produto com ID " + itemDto.produtoId() + " não encontrado."));

      if (produto.getQuantEstoque() < itemDto.quantidade()) {
        throw new RuntimeException("Estoque insuficiente para o produto " + produto.getName());
      }

      produto.setQuantEstoque(produto.getQuantEstoque() - itemDto.quantidade());

      ItemVenda itemVenda = new ItemVenda();
      itemVenda.setProduto(produto);
      itemVenda.setQuantidade(itemDto.quantidade());
      itemVenda.setVenda(novaVenda);

      return itemVenda;

    }).collect(Collectors.toList());

    novaVenda.setItens(itens);
    novaVenda.calcularValorTotal();

    Venda vendaSalva = vendaRepository.save(novaVenda);

    List<ItemVendaResponseDto> itensDto = vendaSalva.getItens().stream()
        .map(item -> new ItemVendaResponseDto(
            item.getId(),
            item.getProduto().getName(),
            item.getQuantidade(),
            item.getProduto().getPreco()
        )).collect(Collectors.toList());

    return new VendaResponseDto(
        vendaSalva.getId(),
        vendaSalva.getData(),
        vendaSalva.getValorTotal(),
        vendaSalva.getFormaPagamento(),
        vendaSalva.getUsuario().getName(),
        itensDto
    );

  }

}