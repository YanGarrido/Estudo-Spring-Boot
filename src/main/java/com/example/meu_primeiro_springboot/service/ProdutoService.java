package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.exceptions.RecursoNaoEncontradoException;
import com.example.meu_primeiro_springboot.model.Estoque;
import com.example.meu_primeiro_springboot.model.Produto;
import com.example.meu_primeiro_springboot.repository.EstoqueRepository;
import com.example.meu_primeiro_springboot.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

  ProdutoRepository produtoRepository;
  EstoqueRepository estoqueRepository;

  public ProdutoService(ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository){

    this.produtoRepository = produtoRepository;
    this.estoqueRepository = estoqueRepository;
  }

  public List<Produto> listarProdutos() {
    return produtoRepository.findAll();
  }

  public Produto buscarProduto(Long id){
    return produtoRepository.findById(id)
        .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID "+id+" não encontrado"));
  }

  @Transactional
  public Produto salvarProduto(Produto produto){
    Produto produtoSalvo = produtoRepository.save(produto);

    Estoque novoEstoque = new Estoque();

    novoEstoque.setProduto(produtoSalvo);
    novoEstoque.setQuantidade(0);

    estoqueRepository.save(novoEstoque);

    return produtoSalvo;
  }

  public void deletarProduto(Long id){

      if (!produtoRepository.existsById(id)){
        throw new RecursoNaoEncontradoException("Produto com ID"+id+" não encontrado");
      }
     produtoRepository.deleteById(id);
  }

  @Transactional
  public Estoque adicionarEstoque(Long id, Integer quantAumentar){
    Produto produto = buscarProduto(id);
    Estoque estoque = produto.getEstoque();

    if(estoque == null){
      estoque = new Estoque();
      estoque.setProduto(produto);
      estoque.setQuantidade(0);
    }

    estoque.setQuantidade(estoque.getQuantidade() + quantAumentar);

    return estoqueRepository.save(estoque);
  }

  @Transactional
  public Estoque reduzirEstoque(Long id, Integer quantReduzir){
    Produto produto = buscarProduto(id);
    Estoque estoque = produto.getEstoque();

    if(estoque == null){
      throw new RuntimeException("Estoque não iniciado");
    }
    int novoEstoque = estoque.getQuantidade() - quantReduzir;

    if (novoEstoque < 0){
      throw  new RuntimeException("Estoque insuficiente");
    }
    estoque.setQuantidade(novoEstoque);

    return estoqueRepository.save(estoque);
  }
}
