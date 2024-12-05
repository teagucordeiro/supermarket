package com.supermarket.service;

import com.supermarket.entity.ClienteEntity;
import com.supermarket.repository.ClienteRepository;
import com.supermarket.dto.ClienteDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClienteRepository clienteRepository;

    public List<ClienteEntity> getAll() {
        return clienteRepository.findAll();
    }

    public ClienteEntity getById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public ClienteEntity create(ClienteDTO clienteDTO) {
        ClienteEntity cliente = new ClienteEntity();
        BeanUtils.copyProperties(clienteDTO, cliente);
        return clienteRepository.save(cliente);
    }

    public ClienteEntity update(ClienteDTO clienteDTO) {
        ClienteEntity cliente = getById(clienteDTO.id());
        BeanUtils.copyProperties(clienteDTO, cliente);
        return clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    public void logicDelete(Long id) {
        ClienteEntity cliente = getById(id);
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }
}
