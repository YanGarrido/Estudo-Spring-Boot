package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.model.Produto;
import com.example.meu_primeiro_springboot.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

  ProdutoRepository produtoRepository;

  public ProdutoService(ProdutoRepository produtoRepository){
    this.produtoRepository = produtoRepository;
  }

  public List<Produto> listarProdutos() {
    return produtoRepository.findAll();
  }

  public Optional<Produto> buscarProduto(Long id){
    return produtoRepository.findById(id);
  }

  public Produto salvarProduto(Produto produto){
    return produtoRepository.save(produto);
  }

  public void deletarProduto(Long id){
     produtoRepository.deleteById(id);
  }
}
