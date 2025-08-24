package com.example.meu_primeiro_springboot.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vendas")
public class Venda {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private  Long id;
  private LocalDate data;
  private Double valorTotal;
  private String formaPagamento;

  @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
  private List<ItemVenda> itens;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  public void calcularValorTotal(){
    this.valorTotal = itens.stream().mapToDouble(item -> item.getProduto()
        .getPreco() * item.getQuantidade()).sum();
  }
  public Venda() {}

  public Venda(LocalDate data, Double valorTotal, String formaPagamento, List<ItemVenda> itens, Usuario usuario){
    this.data = data;
    this.valorTotal = valorTotal;
    this.formaPagamento = formaPagamento;
    this.itens = itens;
    this.usuario = usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public void setFormaPagamento(String formaPagamento) {
    this.formaPagamento = formaPagamento;
  }

  public void setValorTotal(Double valorTotal) {
    this.valorTotal = valorTotal;
  }

  public void setItens(List<ItemVenda> itens) {
    this.itens = itens;
  }

  public List<ItemVenda> getItens() {
    return itens;
  }

  public Long getId() {
    return id;
  }

  public Double getValorTotal() {
    return valorTotal;
  }

  public LocalDate getData() {
    return data;
  }

  public String getFormaPagamento() {
    return formaPagamento;
  }
}
