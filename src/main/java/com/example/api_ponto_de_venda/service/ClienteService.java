package com.example.api_ponto_de_venda.service;

import com.example.api_ponto_de_venda.model.Cliente;
import com.example.api_ponto_de_venda.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
