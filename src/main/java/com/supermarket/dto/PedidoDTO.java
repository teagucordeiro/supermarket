package com.supermarket.dto;

import java.util.List;

public record PedidoDTO (
        Long id,
        String codigo,
    List<Long> produtos,
    Long clientId
) {}
