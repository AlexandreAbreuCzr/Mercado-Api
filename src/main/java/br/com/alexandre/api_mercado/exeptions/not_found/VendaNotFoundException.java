package br.com.alexandre.api_mercado.exeptions.not_found;

public class VendaNotFoundException extends RuntimeException {
    public VendaNotFoundException() {
        super("Venda não encontrada");
    }
}
