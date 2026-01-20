package br.com.alexandre.api_mercado.infra;

import br.com.alexandre.api_mercado.exeptions.geral.PreencherCamposException;
import br.com.alexandre.api_mercado.exeptions.geral.ProdutoComEstoqueException;
import br.com.alexandre.api_mercado.exeptions.not_found.CategoriaNotFoundException;
import br.com.alexandre.api_mercado.exeptions.not_found.EstoqueNotFoundException;
import br.com.alexandre.api_mercado.exeptions.not_found.ProdutoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProdutoNotFoundException.class)
    private ResponseEntity<String> ProdutoNotFoundHandler(ProdutoNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(EstoqueNotFoundException.class)
    private ResponseEntity<String> EstoqueNotFoundHandler(EstoqueNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(CategoriaNotFoundException.class)
    private ResponseEntity<String> CategoriaNotFoundHandler(CategoriaNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<String> UsernameNotFoundHandler(UsernameNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usúario não encontrado");
    }

    @ExceptionHandler(PreencherCamposException.class)
    private ResponseEntity<String> PreencherCamposExceptionHandler(PreencherCamposException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(ProdutoComEstoqueException.class)
    private ResponseEntity<String> ProdutoComEstoqueHandler(ProdutoComEstoqueException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
