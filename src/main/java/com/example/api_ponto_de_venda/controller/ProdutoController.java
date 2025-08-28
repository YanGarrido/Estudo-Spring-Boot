package com.example.api_ponto_de_venda.controller;

import com.example.api_ponto_de_venda.model.Produto;
import com.example.api_ponto_de_venda.service.ProdutoService;
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

  @PutMapping("/{id}/aumentar")
  public ResponseEntity adicionarEstoque(@PathVariable Long id, @RequestParam Integer quantEstoque){
    produtoService.adicionarEstoque(id, quantEstoque);
    return ResponseEntity.ok("Estoque adicionado ao produto");
  }
  @PutMapping("/{id}/reduzir")
  public ResponseEntity reduzirEstoque(@PathVariable Long id, @RequestParam Integer quantEstoque){
    produtoService.reduzirEstoque(id, quantEstoque);
    return ResponseEntity.ok("Estoque do produto reduzido");
  }
}


