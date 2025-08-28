package com.example.api_ponto_de_venda.controller;


import com.example.api_ponto_de_venda.dto.VendaResponseDto;
import com.example.api_ponto_de_venda.dto.VendaRequestDto;
import com.example.api_ponto_de_venda.service.VendaService;
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
