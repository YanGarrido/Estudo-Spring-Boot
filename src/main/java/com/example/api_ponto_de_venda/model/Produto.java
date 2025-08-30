package com.example.api_ponto_de_venda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // indica que essa  Classe é uma Entidade JPA
@Table(name ="produtos") // Indica e defini o nome da tabela
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
  @Id // indica que isso é um ID
  @GeneratedValue(strategy = GenerationType.IDENTITY)// indica que o ID vai ser uma PK gerada automaticamente
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double preco;

  private String descricao;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fornecedor_id")
  private Fornecedor fornecedor;

  @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
  private Estoque estoque;
}

