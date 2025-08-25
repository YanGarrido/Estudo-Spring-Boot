package com.example.meu_primeiro_springboot.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "devolucoes")
public class Devolucao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate data;

  private String motivoDevolucao;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "venda_id")
  private Venda venda;

  public Devolucao(){}

  public Devolucao(LocalDate data, String motivoDevolucao, Venda venda){
    this.data = data;
    this.motivoDevolucao = motivoDevolucao;
    this.venda = venda;
  }

  public void setVenda(Venda venda) {
    this.venda = venda;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public void setMotivoDevolucao(String motivoDevolucao) {
    this.motivoDevolucao = motivoDevolucao;
  }

  public Venda getVenda() {
    return venda;
  }

  public Long getId() {
    return id;
  }

  public LocalDate getData() {
    return data;
  }

  public String getMotivoDevolucao() {
    return motivoDevolucao;
  }
}
