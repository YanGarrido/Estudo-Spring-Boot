package com.example.api_ponto_de_venda.service;

import com.example.api_ponto_de_venda.model.Fornecedor;
import com.example.api_ponto_de_venda.repository.FornecedorRepository;
import com.example.api_ponto_de_venda.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public FornecedorService(FornecedorRepository fornecedorRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.fornecedorRepository = fornecedorRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Fornecedor registrarFornecedor(Fornecedor fornecedor){
        if(usuarioRepository.existsByEmail(fornecedor.getEmail())){
            throw new RuntimeException("Email já registrado");
        }
        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().isEmpty()){
            throw new IllegalArgumentException("CNPJ não pode ser vazio");
        }

        fornecedor.setSenha(passwordEncoder.encode(fornecedor.getSenha()));

        return fornecedorRepository.save(fornecedor);
    }
}
