package com.example.meu_primeiro_springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "logistica")
public class Logistica {
  @Id
  private Long vendaId;

  private String codigoDeRastreio;

  private String status;

  private String enderecoDeEntrega;

  @OneToOne
  @MapsId
  @JoinColumn(name = "venda_id")
  private Venda venda;

  public Logistica(){}

  public Logistica(String codigoDeRastreio, String status, String enderecoDeEntrega){
    this.codigoDeRastreio = codigoDeRastreio;
    this.enderecoDeEntrega = enderecoDeEntrega;
    this.status = status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setVenda(Venda venda) {
    this.venda = venda;
  }

  public void setCodigoDeRastreio(String codigoDeRastreio) {
    this.codigoDeRastreio = codigoDeRastreio;
  }

  public void setEnderecoDeEntrega(String enderecoDeEntrega) {
    this.enderecoDeEntrega = enderecoDeEntrega;
  }

  public String getStatus() {
    return status;
  }

  public Long getVendaId() {
    return vendaId;
  }

  public Venda getVenda() {
    return venda;
  }

  public String getCodigoDeRastreio() {
    return codigoDeRastreio;
  }

  public String getEnderecoDeEntrega() {
    return enderecoDeEntrega;
  }
}
