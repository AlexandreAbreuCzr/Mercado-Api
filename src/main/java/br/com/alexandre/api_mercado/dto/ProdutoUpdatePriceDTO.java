package br.com.alexandre.api_mercado.dto;

import java.math.BigDecimal;

public record ProdutoUpdatePriceDTO(
        BigDecimal price
) {
}
