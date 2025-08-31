package com.example.api_ponto_de_venda.model;

import jakarta.persistence.*;

@Entity // indica que essa  Classe é uma Entidade JPA
@Table(name ="produtos") // Indica e defini o nome da tabela
public class Produto {
  @Id // indica que isso é um ID
  @GeneratedValue(strategy = GenerationType.IDENTITY)// indica que o ID vai ser uma PK gerada automaticamente
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double preco;

  private String descricao;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fornecedor_id")
  private Fornecedor fornecedor;

  @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
  private Estoque estoque;

  public Produto () {}

  public Produto (String name, Double preco, String descricao, Fornecedor fornecedor, Estoque estoque){
    this.name = name;
    this.preco = preco;
    this.descricao = descricao;
    this.fornecedor = fornecedor;
    this.estoque = estoque;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPreco() {
    return preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Estoque getEstoque() {
    return estoque;
  }

  public void setEstoque(Estoque estoque) {
    this.estoque = estoque;
  }

  public void setFornecedor(Fornecedor fornecedor) {
    this.fornecedor = fornecedor;
  }

  public Fornecedor getFornecedor() {
    return fornecedor;
  }

  public String getDescricao() {
    return descricao;
  }
}
