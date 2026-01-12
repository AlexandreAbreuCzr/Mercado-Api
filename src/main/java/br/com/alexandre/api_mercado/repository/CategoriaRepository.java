package br.com.alexandre.api_mercado.repository;

import br.com.alexandre.api_mercado.model.Categoria;
import br.com.alexandre.api_mercado.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByName(String name);
    List<Categoria> findByActive(Boolean active);
}
