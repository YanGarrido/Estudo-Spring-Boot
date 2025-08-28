package com.example.api_ponto_de_venda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pagamentos")
public class Pagamento {
  @Id
  private Long vendaId;

  private String metodoPagamento;

  private String status;

  @OneToOne
  @MapsId
  @JoinColumn(name = "venda_id")
  private Venda venda;

  public Pagamento() {
  }

  public Pagamento(String metodoPagamento, String status, Venda venda) {
    this.metodoPagamento = metodoPagamento;
    this.status = status;
    this.venda = venda;
  }

  public void setVenda(Venda venda) {
    this.venda = venda;
  }

  public void setMetodoPagamento(String metodoPagamento) {
    this.metodoPagamento = metodoPagamento;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Venda getVenda() {
    return venda;
  }

  public Long getVendaId() {
    return vendaId;
  }

  public String getMetodoPagamento() {
    return metodoPagamento;
  }

  public String getStatus() {
    return status;
  }
}
