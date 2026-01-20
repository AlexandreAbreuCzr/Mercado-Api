package br.com.alexandre.api_mercado.dto;

public record EstoqueCreateDTO(
    Long produtoId,
    Integer quantity
) { }
