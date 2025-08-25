package com.example.meu_primeiro_springboot.repository;

import com.example.meu_primeiro_springboot.model.Logistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticaRepository extends JpaRepository<Logistica, Long> {
}
