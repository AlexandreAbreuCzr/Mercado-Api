package br.com.alexandre.api_mercado.controller;

import br.com.alexandre.api_mercado.dto.*;
import br.com.alexandre.api_mercado.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> create(
            @RequestBody @Valid CategoriaCreateDTO dto) {

        return ResponseEntity.status(201).body(categoriaService.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> getAll() {
        return ResponseEntity.ok(categoriaService.getAll());
    }

    @GetMapping("/nome/{name}")
    public ResponseEntity<List<CategoriaResponseDTO>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(categoriaService.findByName(name));
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<CategoriaResponseDTO>> getAtivas() {
        return ResponseEntity.ok(categoriaService.findByActive(true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaUpdateDTO dto) {

        return ResponseEntity.ok(categoriaService.update(id, dto));
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<CategoriaResponseDTO> updateName(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaUpdateNameDTO dto) {

        return ResponseEntity.ok(categoriaService.updateName(id, dto));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<CategoriaResponseDTO> updateActive(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaUpdateActiveDTO dto) {

        return ResponseEntity.ok(categoriaService.updateActive(id, dto));
    }
}

