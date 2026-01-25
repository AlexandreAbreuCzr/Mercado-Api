package br.com.alexandre.api_mercado.repository;

import br.com.alexandre.api_mercado.model.Venda;
import br.com.alexandre.api_mercado.model.VendaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaItemRepository extends JpaRepository<VendaItem, Long> {
    @Query("""
    SELECT DISTINCT vi.venda
    FROM VendaItem vi
    WHERE vi.produto.name = :name
""")
    List<Venda> findVendasByProductName(@Param("name") String name);

}
