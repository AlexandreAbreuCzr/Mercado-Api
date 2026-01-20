package br.com.alexandre.api_mercado.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "estoque")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto product;

    @Column(nullable = false)
    private Integer quantity;

    private LocalDate lastUpdate = LocalDate.now();

    private void setUpdate(){
        this.lastUpdate = LocalDate.now();
    }
}
