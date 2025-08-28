package com.example.api_ponto_de_venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.api_ponto_de_venda.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
