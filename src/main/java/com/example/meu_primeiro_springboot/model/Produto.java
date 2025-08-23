package com.example.meu_primeiro_springboot.model;

import jakarta.persistence.*;

@Entity // indica que essa  Classe é uma Entidade JPA
@Table(name ="produtos") // Indica e defini o nome da tabela
public class Produto {
  @Id // indica que isso é um ID
  @GeneratedValue(strategy = GenerationType.IDENTITY)// indica que o ID vai ser uma PK gerada automaticamente
  private Long id;

  private String name;
  private Double preco;
  private Integer quantEstoque;

  public Produto () {}

  public Produto (String name, Double preco, Integer quantEstoque){
    this.name = name;
    this.preco = preco;
    this.quantEstoque = quantEstoque;
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

  public void setQuantEstoque(Integer quantEstoque) {
    this.quantEstoque = quantEstoque;
  }

  public Integer getQuantEstoque() {
    return quantEstoque;
  }
}

