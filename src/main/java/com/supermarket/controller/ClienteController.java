package com.supermarket.controller;

import com.supermarket.dto.ClienteDTO;
import com.supermarket.entity.ClienteEntity;
import com.supermarket.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> getAll() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> getById(@PathVariable Long id) {
        ClienteEntity cliente = clientService.getById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ClienteEntity postCliente(@RequestBody ClienteDTO clienteDTO) {
        return clientService.create(clienteDTO);
    }

    @PutMapping
    public ClienteEntity putCliente(@RequestBody ClienteDTO clienteDTO) {
        return clientService.update(clienteDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clientService.delete(id);
    }

    @PatchMapping("/{id}/logic-delete")
    public void deleteLogic(@PathVariable Long id) {
        clientService.logicDelete(id);
    }
}
