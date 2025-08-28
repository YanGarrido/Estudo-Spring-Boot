package com.example.api_ponto_de_venda.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

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

  @OneToMany(mappedBy = "devolucao", cascade = CascadeType.ALL)
  private List<ItemDevolucao> itensDevolvidos;


  public Devolucao(){}

  public Devolucao(LocalDate data, String motivoDevolucao, Venda venda, List<ItemDevolucao> itensDevolvidos){
    this.data = data;
    this.motivoDevolucao = motivoDevolucao;
    this.venda = venda;
    this.itensDevolvidos = itensDevolvidos;
  }

  public void setItensDevolvidos(List<ItemDevolucao> itensDevolvidos) {
    this.itensDevolvidos = itensDevolvidos;
  }

  public List<ItemDevolucao> getItensDevolvidos() {
    return itensDevolvidos;
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
