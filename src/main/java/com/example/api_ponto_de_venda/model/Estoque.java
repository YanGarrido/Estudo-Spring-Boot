package com.example.api_ponto_de_venda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estoque")
public class Estoque {
  @Id
  private Long id;

  private Integer quantidade;

  @OneToOne
  @MapsId
  @JoinColumn(name = "produto_id")
  private Produto produto;

  public Estoque(){}

  public Estoque(Integer quantidade, Produto produto){
    this.quantidade = quantidade;
    this.produto = produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  public Produto getProduto() {
    return produto;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public Integer getQuantidade() {
    return quantidade;
  }
}