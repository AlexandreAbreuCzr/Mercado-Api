package br.com.alexandre.api_mercado.dto;

public record VendaItemCreateDTO(
        Long productId,
        Integer quantity
) {
}
