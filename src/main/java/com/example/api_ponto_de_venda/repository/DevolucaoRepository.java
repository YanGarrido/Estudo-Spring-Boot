package com.example.api_ponto_de_venda.repository;

import com.example.api_ponto_de_venda.model.Devolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolucaoRepository extends JpaRepository<Devolucao, Long> {
}
