package br.com.alexandre.api_mercado.controller;

import br.com.alexandre.api_mercado.dto.CategoriaCreateDTO;
import br.com.alexandre.api_mercado.dto.CategoriaResponseDTO;
import br.com.alexandre.api_mercado.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CategoriaCreateDTO params){
        try {
            var result = categoriaService.save(params);
            return ResponseEntity.ok(result);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            var result = categoriaService.findById(id);
            return ResponseEntity.ok(result);
        }catch (RuntimeException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @GetMapping("/get")
    public List<CategoriaResponseDTO> get(@RequestParam(required = false) String name, @RequestParam(required = false) Boolean active){
        if(name != null && active != null){
            throw new IllegalArgumentException("Use apenas um filtro por vez");
        }

        if (name !=null){
            return categoriaService.findByName(name);
        }

        if (active !=null){
            return categoriaService.findByActive(active);
        }

        return categoriaService.getAll();
    }

}
