package com.supermarket.controller;

import com.supermarket.dto.PedidoDTO;
import com.supermarket.entity.PedidoEntity;
import com.supermarket.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @GetMapping
    public List<PedidoEntity> getAll() {
        return pedidoService.getAll();
    }

    @GetMapping("/{id}")
    public PedidoEntity getById(@PathVariable Long id) {
        return pedidoService.getById(id);
    }

    @PostMapping
    public PedidoEntity postPedido(@RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.create(pedidoDTO);
    }

    @PutMapping
    public PedidoEntity putPedido(@RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.update(pedidoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
    }

    @PatchMapping("/{id}/logic-delete")
    public void deleteLogic(@PathVariable Long id) {
        pedidoService.logicDelete(id);
    }
}