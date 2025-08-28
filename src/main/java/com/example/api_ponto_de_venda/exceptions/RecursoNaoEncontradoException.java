package com.example.api_ponto_de_venda.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {
  public RecursoNaoEncontradoException(String mensagem){
    super(mensagem);
  }
}
