package com.example.meu_primeiro_springboot.model;

import jakarta.persistence.*;


@Entity
@Table(name = "itens_devolucao")
public class ItemDevolucao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "produto_id", nullable = false)
  private Produto produto;

  @Column(nullable = false)
  private Integer quantidade;

  public ItemDevolucao(){}

  public ItemDevolucao(Produto produto, Integer quantidade){
    this.produto = produto;
    this.quantidade = quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
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

}

