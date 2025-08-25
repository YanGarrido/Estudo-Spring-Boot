package com.example.meu_primeiro_springboot.repository;

import com.example.meu_primeiro_springboot.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
