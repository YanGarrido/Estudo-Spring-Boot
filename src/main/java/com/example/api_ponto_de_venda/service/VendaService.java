package com.example.api_ponto_de_venda.service;


import com.example.api_ponto_de_venda.dto.ItemVendaResponseDto;
import com.example.api_ponto_de_venda.dto.VendaRequestDto;
import com.example.api_ponto_de_venda.dto.VendaResponseDto;
import com.example.api_ponto_de_venda.model.*;
import com.example.api_ponto_de_venda.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VendaService {

  private final VendaRepository vendaRepository;
  private final ProdutoRepository produtoRepository;
  private final ClienteRepository clienteRepository;
  private final EstoqueRepository estoqueRepository;


  public VendaService(VendaRepository vendaRepository,
                      ProdutoRepository produtoRepository,
                      ClienteRepository clienteRepository,
                      EstoqueRepository estoqueRepository) {

    this.vendaRepository = vendaRepository;
    this.produtoRepository = produtoRepository;
    this.clienteRepository = clienteRepository;
    this.estoqueRepository = estoqueRepository;

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
              venda.getPagamento().getMetodoPagamento(),
              venda.getCliente().getName(),
              itensDto
      );
    }).collect(Collectors.toList());
  }


  @Transactional
  public VendaResponseDto adicionarVenda(VendaRequestDto dto, String cpf) {
    Cliente cliente = clienteRepository.findByCpf(cpf)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + cpf));

    Venda novaVenda = new Venda();
    novaVenda.setCliente(cliente);
    novaVenda.setData(LocalDate.now());
    //novaVenda.setFormaPagamento(dto.formaPagamento());
    Pagamento pagamento = new Pagamento();
    pagamento.setMetodoPagamento(dto.metodoPagamento());

    List<ItemVenda> itens = dto.itens().stream().map(itemDto -> {
      Produto produto = produtoRepository.findById(itemDto.produtoId())
          .orElseThrow(() -> new RuntimeException("Produto com ID " + itemDto.produtoId() + " não encontrado."));
      Estoque estoque = estoqueRepository.findById(itemDto.produtoId())
              .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
      if (estoque.getQuantidade() < itemDto.quantidade()) {
        throw new RuntimeException("Estoque insuficiente para o produto " + produto.getName());
      }

      estoque.setQuantidade(estoque.getQuantidade() - itemDto.quantidade());

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
        vendaSalva.getPagamento().getMetodoPagamento(),
        vendaSalva.getCliente().getName(),
        itensDto
    );

  }

}