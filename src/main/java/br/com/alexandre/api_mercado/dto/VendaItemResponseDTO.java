package br.com.alexandre.api_mercado.dto;

import java.math.BigDecimal;
import java.util.List;

public record VendaItemResponseDTO(
        Long id,
        Long produtoId,
        String produtoNome,
        Integer quantity,
        BigDecimal price,
        BigDecimal subTotal
) {}

