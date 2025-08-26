package com.example.meu_primeiro_springboot.repository;

import com.example.meu_primeiro_springboot.model.ItemDevolucao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDevolucaoRepository extends JpaRepository<ItemDevolucao, Long> {
}
