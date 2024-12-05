package com.supermarket.dto;

import java.time.LocalDate;

public record ClienteDTO(
        Long id,
        String nome,
        String cpf,
        String genero,
        LocalDate dataNascimento
) {}