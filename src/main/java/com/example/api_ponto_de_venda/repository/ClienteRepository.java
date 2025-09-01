package com.example.api_ponto_de_venda.repository;

import com.example.api_ponto_de_venda.model.Cliente;
import com.example.api_ponto_de_venda.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);

    Optional<Cliente> findByCpf(String cpf);

    boolean existsByEmail(String email);

    Optional<Cliente> findByEmail(String email);


}
