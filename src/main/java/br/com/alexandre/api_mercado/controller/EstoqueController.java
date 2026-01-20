package br.com.alexandre.api_mercado.controller;

import br.com.alexandre.api_mercado.dto.EstoqueCreateDTO;
import br.com.alexandre.api_mercado.dto.EstoqueResponseDTO;
import br.com.alexandre.api_mercado.dto.EstoqueUpdateDTO;
import br.com.alexandre.api_mercado.service.EstoqueService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public ResponseEntity<List<EstoqueResponseDTO>> getAll() {
        return ResponseEntity.ok(estoqueService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(estoqueService.findById(id));
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<EstoqueResponseDTO> getByProdutoId(@PathVariable Long produtoId) {
        return ResponseEntity.ok(estoqueService.findByProductId(produtoId));
    }

    @GetMapping("/produto")
    public ResponseEntity<EstoqueResponseDTO> getByProdutoNome(@RequestParam String nome) {
        return ResponseEntity.ok(estoqueService.getEstoqueByProductName(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> update(@PathVariable Long id, @RequestBody @Valid EstoqueUpdateDTO estoqueUpdateDTO){
        return ResponseEntity.ok(estoqueService.update(id, estoqueUpdateDTO));
    }
}
