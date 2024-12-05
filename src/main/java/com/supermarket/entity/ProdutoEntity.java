package com.supermarket.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProduto;

    private String marca;

    private LocalDate dataFabricacao;
    private LocalDate dataValidade;

    @Enumerated(EnumType.STRING)
    private GeneroProduto genero;

    @Column(nullable = false)
    private String lote;

    @Column(nullable = false)
    private boolean ativo = true;
}

