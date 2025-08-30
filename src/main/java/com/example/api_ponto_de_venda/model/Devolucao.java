package com.example.api_ponto_de_venda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "devolucoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}