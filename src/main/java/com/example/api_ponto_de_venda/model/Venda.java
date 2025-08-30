package com.example.api_ponto_de_venda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vendas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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

  public void calcularValorTotal() {
    this.valorTotal = itens.stream()
        .mapToDouble(item -> item.getPreco() * item.getQuantidade())
        .sum();
  }
}