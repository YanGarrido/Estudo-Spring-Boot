package com.example.api_ponto_de_venda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "logistica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

  public Logistica() {
  }

}