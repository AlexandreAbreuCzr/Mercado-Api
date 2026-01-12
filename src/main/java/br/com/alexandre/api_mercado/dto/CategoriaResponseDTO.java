package br.com.alexandre.api_mercado.dto;

import java.util.List;

public record CategoriaResponseDTO(
    Long id,
    String name,
    Boolean active,
    List<ProdutoResponseDTO> produtos
) {}
