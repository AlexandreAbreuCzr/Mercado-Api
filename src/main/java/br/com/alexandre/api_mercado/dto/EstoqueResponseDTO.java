package br.com.alexandre.api_mercado.dto;

import java.time.LocalDate;

public record EstoqueResponseDTO (
        Long id,
        ProdutoResponseDTO produto,
        Integer quantity,
        LocalDate lastUpadate
){
}
