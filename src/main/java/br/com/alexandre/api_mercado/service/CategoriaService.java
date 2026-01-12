package br.com.alexandre.api_mercado.service;

import br.com.alexandre.api_mercado.dto.CategoriaCreateDTO;
import br.com.alexandre.api_mercado.dto.CategoriaResponseDTO;
import br.com.alexandre.api_mercado.dto.ProdutoResponseDTO;
import br.com.alexandre.api_mercado.model.Categoria;
import br.com.alexandre.api_mercado.model.Produto;
import br.com.alexandre.api_mercado.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaResponseDTO> getAll() {
        return categoriaRepository.findAll().stream()
                .map(this::mapCategoria)
                .toList();
    }

    public List<CategoriaResponseDTO> findByName(String name) {
        return categoriaRepository.findByName(name).stream()
                .map(this::mapCategoria)
                .toList();
    }

    public List<CategoriaResponseDTO> findByActive(Boolean active) {
        return categoriaRepository.findByActive(active).stream()
                .map(this::mapCategoria)
                .toList();
    }

    public CategoriaResponseDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return mapCategoria(categoria);
    }


    public CategoriaResponseDTO save(CategoriaCreateDTO dto){
        if (dto.name() == null){
            throw new IllegalArgumentException("Preencher Todos os campos");
        }

        Categoria categoria = new Categoria();
        categoria.setName(dto.name());

        Categoria salvo = categoriaRepository.save(categoria);
        return new CategoriaResponseDTO(
                salvo.getId(),
                salvo.getName(),
                salvo.getActive(),
                Collections.emptyList()
        );
    }


    private List<ProdutoResponseDTO> mapProdutos(List<Produto> produtos) {
        return produtos.stream()
                .map(p -> new ProdutoResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getData_created(),
                        p.getCategoria().getId()
                ))
                .toList();
    }

    private CategoriaResponseDTO mapCategoria(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getName(),
                categoria.getActive(),
                mapProdutos(categoria.getProdutos())
        );
    }

}