package com.example.meu_primeiro_springboot.controller;


import com.example.meu_primeiro_springboot.dto.VendaResponseDto;
import com.example.meu_primeiro_springboot.dto.VendaRequestDto;
import com.example.meu_primeiro_springboot.model.Venda;
import com.example.meu_primeiro_springboot.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

    import java.util.List;

@RestController
@RequestMapping("api/vendas")
public class VendaController {
  private final VendaService vendaService;

  public VendaController(VendaService vendaService){
    this.vendaService = vendaService;
  }

  @GetMapping
  public List<VendaResponseDto> buscarVendas(){
    return vendaService.listarVendas();
  }

  @PostMapping()
  public ResponseEntity<VendaResponseDto> criarVenda(@RequestBody VendaRequestDto vendaDto, Authentication authentication){
    String emailUsuario = authentication.getName();
    VendaResponseDto novaVenda = vendaService.adicionarVenda(vendaDto, emailUsuario);
    return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
  }
}
