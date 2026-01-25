package br.com.alexandre.api_mercado.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VendaResponseDTO(
        Long id,
        List<VendaItemResponseDTO> items,
        BigDecimal totalPrice,
        LocalDateTime createdAt,
        LocalDateTime lastUpdate
) {
}
