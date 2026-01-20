package br.com.alexandre.api_mercado.dto;

import java.math.BigDecimal;

public record ProdutoUpdateDTO(
        String name,
        BigDecimal price,
        Long categoriaId
) {
}
