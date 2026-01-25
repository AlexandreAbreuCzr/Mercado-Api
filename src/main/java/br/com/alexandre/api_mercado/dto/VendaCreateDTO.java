package br.com.alexandre.api_mercado.dto;

import java.util.List;

public record VendaCreateDTO(
        List<VendaItemCreateDTO> items
) {

}
