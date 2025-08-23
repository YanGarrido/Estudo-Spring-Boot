package com.example.meu_primeiro_springboot.controller;

import com.example.meu_primeiro_springboot.exceptions.RecursoNaoEncontradoException;
import com.example.meu_primeiro_springboot.model.Produto;
import com.example.meu_primeiro_springboot.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  private final ProdutoService produtoService;

  public ProdutoController(ProdutoService produtoService){
    this.produtoService = produtoService;
  }
  
  @GetMapping
  public List<Produto> listarProduto(){
    return produtoService.listarProdutos();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> buscarProduto(@PathVariable Long id){
      Produto produto = produtoService.buscarProduto(id);
      return ResponseEntity.ok(produto);
  }

  @PostMapping
  public  Produto criarProduto(@RequestBody Produto produto){
    return produtoService.salvarProduto(produto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deletarProduto(@PathVariable Long id){
      produtoService.deletarProduto(id);
    return ResponseEntity.noContent().build();
  }
}


