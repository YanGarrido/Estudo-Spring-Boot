package com.example.api_ponto_de_venda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "itemVenda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVenda {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "venda_id")
  private Venda venda;

  @ManyToOne
  @JoinColumn(name = "produto_id")
  private Produto produto;

  private Integer quantidade;

  private Double precoUnitario;

  public ItemVenda() {
  }
}