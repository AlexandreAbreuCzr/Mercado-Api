package br.com.alexandre.api_mercado.service;

import br.com.alexandre.api_mercado.dto.*;
import br.com.alexandre.api_mercado.exeptions.not_found.CategoriaNotFoundException;
import br.com.alexandre.api_mercado.exeptions.geral.PreencherCamposException;
import br.com.alexandre.api_mercado.model.Categoria;
import br.com.alexandre.api_mercado.model.Produto;
import br.com.alexandre.api_mercado.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoService produtoService;

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
                .orElseThrow(CategoriaNotFoundException::new);
        return mapCategoria(categoria);
    }


    public CategoriaResponseDTO save(CategoriaCreateDTO dto){
        if (dto.name() == null){
            throw new PreencherCamposException();
        }

        Categoria categoria = new Categoria();
        categoria.setName(dto.name());

        Categoria salvo = categoriaRepository.save(categoria);
        return new CategoriaResponseDTO(
                salvo.getId(),
                salvo.getName(),
                salvo.getActive(),
                Collections.emptyList(),
                salvo.getCreatedAt(),
                salvo.getLastUpdate()
        );
    }


    private List<ProdutoResponseDTO> mapProdutos(List<Produto> produtos) {
        return produtos.stream()
                .map(p -> new ProdutoResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getCategoria().getId(),
                        p.getCreatedAt(),
                        p.getLastUpdate()
                ))
                .toList();
    }

    private CategoriaResponseDTO mapCategoria(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getName(),
                categoria.getActive(),
                mapProdutos(categoria.getProdutos()),
                categoria.getCreatedAt(),
                categoria.getLastUpdate()
        );
    }

    @Transactional
    public void delete(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(CategoriaNotFoundException::new);

        List<Produto> produtos = categoria.getProdutos();

        if (produtos.size() > 0) {
            for (Produto produto : produtos) {
                produtoService.delete(produto.getId());
            }
        }

        categoriaRepository.delete(categoria);
    }

    public CategoriaResponseDTO update(Long categoriaId, CategoriaUpdateDTO dto){
        if (dto.name() == null || dto.active() == null){
            throw new PreencherCamposException();
        }
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(CategoriaNotFoundException::new);
        categoria.setName(dto.name());
        categoria.setActive(dto.active());
        Categoria salvo = categoriaRepository.save(categoria);
        return mapCategoria(categoria);
    }

    public CategoriaResponseDTO updateName(Long categoriaId, CategoriaUpdateNameDTO dto){
        if (dto.name() == null){
            throw new PreencherCamposException();
        }
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(CategoriaNotFoundException::new);
        categoria.setName(dto.name());
        Categoria salvo = categoriaRepository.save(categoria);
        return mapCategoria(categoria);
    }

    public CategoriaResponseDTO updateActive(Long categoriaId, CategoriaUpdateActiveDTO dto){
        if (dto.active() == null){
            throw new PreencherCamposException();
        }
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(CategoriaNotFoundException::new);
        categoria.setActive(dto.active());
        Categoria salvo = categoriaRepository.save(categoria);
        return mapCategoria(categoria);
    }
}