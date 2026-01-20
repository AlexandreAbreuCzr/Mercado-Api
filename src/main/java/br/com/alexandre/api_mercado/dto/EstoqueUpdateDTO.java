package br.com.alexandre.api_mercado.dto;

public record EstoqueUpdateDTO(
    Long produtoId,
    Integer quantity
) { }
