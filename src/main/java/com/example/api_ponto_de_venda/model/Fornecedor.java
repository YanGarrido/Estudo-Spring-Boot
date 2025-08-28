package com.example.api_ponto_de_venda.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "fornecedores")
public class Fornecedor extends Usuario {
  @Column(unique = true)
  private String cnpj;

  private String nomeDaEmpresa;

  @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Produto> produtoFornecidos;

  public Fornecedor() {}

  public Fornecedor(String cnpj, String nomeDaEmpresa, List<Produto> produtoFornecidos){
    this.cnpj = cnpj;
    this.nomeDaEmpresa = nomeDaEmpresa;
    this.produtoFornecidos = produtoFornecidos;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setNomeDaEmpresa(String nomeDaEmpresa) {
    this.nomeDaEmpresa = nomeDaEmpresa;
  }

  public String getNomeDaEmpresa() {
    return nomeDaEmpresa;
  }

  public void setProdutoFornecidos(List<Produto> produtoFornecidos) {
    this.produtoFornecidos = produtoFornecidos;
  }

  public List<Produto> getProdutoFornecidos() {
    return produtoFornecidos;
  }
}
