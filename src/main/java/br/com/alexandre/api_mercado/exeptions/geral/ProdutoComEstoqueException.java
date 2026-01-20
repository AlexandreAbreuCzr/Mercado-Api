package br.com.alexandre.api_mercado.exeptions.geral;

public class ProdutoComEstoqueException extends RuntimeException {
    public ProdutoComEstoqueException() {
        super("Não é possivel excluir um produto com estoque.");
    }
}
