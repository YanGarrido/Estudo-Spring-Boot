package com.example.meu_primeiro_springboot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {

  private String telefone;

  private String endereco;

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Venda> historicoDeCompras;

  public  Cliente(){}

  public Cliente(String telefone,String endereco, List<Venda> historicoDeCompras){
    this.endereco = endereco;
    this.telefone = telefone;
    this.historicoDeCompras = historicoDeCompras;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public void setHistoricoDeCompras(List<Venda> historicoDeCompras) {
    this.historicoDeCompras = historicoDeCompras;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public List<Venda> getHistoricoDeCompras() {
    return historicoDeCompras;
  }


  public String getTelefone() {
    return telefone;
  }
}
