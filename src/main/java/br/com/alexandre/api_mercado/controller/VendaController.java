package br.com.alexandre.api_mercado.controller;

import br.com.alexandre.api_mercado.dto.VendaCreateDTO;
import br.com.alexandre.api_mercado.dto.VendaResponseDTO;
import br.com.alexandre.api_mercado.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> getAll(){
        return ResponseEntity.ok(vendaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(vendaService.findVendaById(id));
    }

    @GetMapping("/productName/{productName}")
    public ResponseEntity<List<VendaResponseDTO>> findByProductName(@PathVariable String productName){
        return ResponseEntity.ok(vendaService.findByProductName(productName));
    }

    @PostMapping
    public ResponseEntity<VendaResponseDTO> saveVenda(@RequestBody VendaCreateDTO dto){
        return ResponseEntity.status(201).body(vendaService.criarVenda(dto));
    }
}
