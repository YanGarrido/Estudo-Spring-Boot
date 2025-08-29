package com.example.api_ponto_de_venda.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {

  private String telefone;
  private String cpf;


  private String endereco;

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Venda> historicoDeCompras;

  public  Cliente(){}

  public Cliente(String cpf, String telefone,String endereco, List<Venda> historicoDeCompras){
    this.endereco = endereco;
    this.telefone = telefone;
    this.historicoDeCompras = historicoDeCompras;
    this.cpf = cpf;
  }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
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
