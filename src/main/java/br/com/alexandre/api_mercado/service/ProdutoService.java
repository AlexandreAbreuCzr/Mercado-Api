package br.com.alexandre.api_mercado.service;

import br.com.alexandre.api_mercado.dto.ProdutoCreateDTO;
import br.com.alexandre.api_mercado.dto.ProdutoResponseDTO;
import br.com.alexandre.api_mercado.model.Produto;
import br.com.alexandre.api_mercado.repository.CategoriaRepository;
import br.com.alexandre.api_mercado.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    public ProdutoResponseDTO save(ProdutoCreateDTO dto) {
        if (dto.name() == null || dto.price() == null) {
            throw new IllegalArgumentException("Preencher todos os campos");
        }

        Produto produto = new Produto();
        produto.setName(dto.name());
        produto.setPrice(dto.price());
        produto.setCategoria(
                categoriaRepository.findById(dto.categoriaId())
                        .orElseThrow(() -> new RuntimeException("Categoria não encontrada"))
        );

        Produto salvo = produtoRepository.save(produto);
        return mapProduto(salvo);
    }

    public List<ProdutoResponseDTO> getAll() {
        return produtoRepository.findAll().stream()
                .map(this::mapProduto)
                .toList();
    }

    public ProdutoResponseDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return mapProduto(produto);
    }

    public List<ProdutoResponseDTO> findByName(String name) {
        return produtoRepository.findByName(name).stream()
                .map(this::mapProduto)
                .toList();
    }

    public List<ProdutoResponseDTO> findByPrice(BigDecimal price) {
        return produtoRepository.findByPrice(price).stream()
                .map(this::mapProduto)
                .toList();
    }


    private ProdutoResponseDTO mapProduto(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getName(),
                produto.getPrice(),
                produto.getData_created(),
                produto.getCategoria().getId()
        );
    }

}
