package com.supermarket.dto;


import java.time.LocalDate;

public record ProdutoDTO(
        Long id,
        String nomeProduto,
        String marca,
        LocalDate dataFabricacao,
        LocalDate dataValidade,
        String genero,
        String lote
) {}