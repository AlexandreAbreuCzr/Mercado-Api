package br.com.alexandre.api_mercado.exeptions.not_found;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException() {
        super("Produto não encontrado");
    }
}
