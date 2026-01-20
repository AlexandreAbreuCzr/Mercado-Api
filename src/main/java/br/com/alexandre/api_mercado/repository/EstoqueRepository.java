package br.com.alexandre.api_mercado.repository;

import br.com.alexandre.api_mercado.model.Estoque;
import br.com.alexandre.api_mercado.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByProduct(Produto produto);
}
