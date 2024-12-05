package com.supermarket.controller;

import com.supermarket.dto.ClienteDTO;
import com.supermarket.entity.ClienteEntity;
import com.supermarket.repository.ClienteRepository;
import com.supermarket.entity.GeneroCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<ClienteEntity> getAll() {
        return clienteRepository.findAll().stream().filter(ClienteEntity::isAtivo).toList();
    }

    @GetMapping("/{id}")
    public ClienteEntity getById(@PathVariable Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ClienteEntity postCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteEntity cliente = new ClienteEntity(null, clienteDTO.nome(), clienteDTO.cpf(), GeneroCliente.valueOf(clienteDTO.genero()), clienteDTO.dataNascimento(), true);
        return clienteRepository.save(cliente);
    }

    @PutMapping
    public ClienteEntity putCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteEntity cliente = clienteRepository.findById(clienteDTO.id()).orElse(null);
        if (cliente != null && cliente.isAtivo()) {
            cliente.setNome(clienteDTO.nome());
            cliente.setCpf(clienteDTO.cpf());
            cliente.setGenero(GeneroCliente.valueOf(clienteDTO.genero()));
            cliente.setDataNascimento(clienteDTO.dataNascimento());
            return clienteRepository.save(cliente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }

    @DeleteMapping("/logic/{id}")
    public void deleteLogic(@PathVariable Long id) {
        ClienteEntity cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setAtivo(false);
            clienteRepository.save(cliente);
        }
    }
}
