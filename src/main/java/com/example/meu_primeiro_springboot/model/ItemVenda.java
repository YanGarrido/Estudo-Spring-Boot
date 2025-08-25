package com.example.meu_primeiro_springboot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ItemVenda {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "venda_id")
  private Venda venda;

  @ManyToOne
  @JoinColumn(name = "produto_id")
  private Produto produto;

  private Integer quantidade;

  private Double precoUnitario;

  public ItemVenda() {
  }

  public ItemVenda(Venda venda, Produto produto, Integer quantidade){
    this.venda = venda;
    this.quantidade = quantidade;
    this.produto = produto;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public void setVenda(Venda venda) {
    this.venda = venda;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  public Long getId() {
    return id;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public Produto getProduto() {
    return produto;
  }

  public Venda getVenda() {
    return venda;
  }
}