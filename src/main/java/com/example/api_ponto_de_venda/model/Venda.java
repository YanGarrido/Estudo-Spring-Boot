package com.example.api_ponto_de_venda.model;


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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cliente_id", nullable = false)
  private Cliente cliente;

  @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
  private List<ItemVenda> itens;

  @OneToOne(mappedBy = "venda", cascade = CascadeType.ALL)
  private Pagamento pagamento;

  @OneToOne(mappedBy = "venda", cascade = CascadeType.ALL)
  private Logistica logistica;


  public void calcularValorTotal(){
    this.valorTotal = itens.stream().mapToDouble(item -> item.getProduto()
        .getPreco() * item.getQuantidade()).sum();
  }
  public Venda() {}

  public Venda(LocalDate data, Double valorTotal, List<ItemVenda> itens, Cliente cliente, Pagamento pagamento, Logistica logistica){
    this.data = data;
    this.valorTotal = valorTotal;
    this.pagamento = pagamento;
    this.logistica = logistica;
    this.itens = itens;
    this.cliente = cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setLogistica(Logistica logistica) {
    this.logistica = logistica;
  }

  public void setPagamento(Pagamento pagamento) {
    this.pagamento = pagamento;
  }

  public Logistica getLogistica() {
    return logistica;
  }

  public Pagamento getPagamento() {
    return pagamento;
  }

  public void setData(LocalDate data) {
    this.data = data;
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

}