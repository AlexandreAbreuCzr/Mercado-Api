package br.com.alexandre.api_mercado.repository;

import br.com.alexandre.api_mercado.model.Venda;
import br.com.alexandre.api_mercado.model.VendaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
