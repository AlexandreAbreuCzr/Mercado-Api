package br.com.alexandre.api_mercado.exeptions.geral;

public class PreencherCamposException extends RuntimeException{
    public PreencherCamposException(){
        super("Preencher todos os campos");
    }
}
