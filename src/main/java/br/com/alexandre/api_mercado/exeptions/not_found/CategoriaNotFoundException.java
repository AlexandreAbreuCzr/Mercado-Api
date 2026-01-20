package br.com.alexandre.api_mercado.exeptions.not_found;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException() {
        super("Categoria não encontrada");
    }
}
