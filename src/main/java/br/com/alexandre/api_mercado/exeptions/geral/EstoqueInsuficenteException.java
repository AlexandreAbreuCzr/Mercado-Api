package br.com.alexandre.api_mercado.exeptions.geral;

public class EstoqueInsuficenteException extends RuntimeException {
    public EstoqueInsuficenteException() {
        super("Estoque insuficiente");
    }
}
