package com.example.meu_primeiro_springboot.repository;

import com.example.meu_primeiro_springboot.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
