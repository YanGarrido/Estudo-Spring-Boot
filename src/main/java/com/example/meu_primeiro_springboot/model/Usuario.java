package com.example.meu_primeiro_springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED) // define estrategia de herança
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String senha;


  public Usuario () {};

  public Usuario(String name, String email, String senha){
    this.name = name;
    this.email = email;
    this.senha = senha;
  }

  public Long getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getSenha() {
    return senha;
  }
}
