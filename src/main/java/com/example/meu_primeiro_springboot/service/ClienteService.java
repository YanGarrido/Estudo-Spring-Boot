package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.model.Cliente;
import com.example.meu_primeiro_springboot.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

public class ClienteService {
  private final ClienteRepository clienteRepository;

  public ClienteService(ClienteRepository clienteRepository){
    this.clienteRepository = clienteRepository;
  }
  public Cliente salvarCliente(Cliente cliente) {
    return clienteRepository.save(cliente);
  }
  public List<Cliente> listarCliente(){
    return clienteRepository.findAll();
  }
  public Optional<Cliente> buscarClienteId(Long id){
    return clienteRepository.findById(id);
  }


}
