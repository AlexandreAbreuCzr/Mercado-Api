package br.com.alexandre.api_mercado.exeptions.not_found;

public class EstoqueNotFoundException extends RuntimeException {
    public EstoqueNotFoundException() {
        super("Estoque não encontrado");
    }
}
