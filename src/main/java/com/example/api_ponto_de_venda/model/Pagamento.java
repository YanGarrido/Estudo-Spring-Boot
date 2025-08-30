package com.example.api_ponto_de_venda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {
  @Id
  private Long vendaId;

  private String metodoPagamento;

  private String status;

  @OneToOne
  @MapsId
  @JoinColumn(name = "venda_id")
  private Venda venda;

}
