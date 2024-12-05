package com.supermarket.service;

import com.supermarket.dto.PedidoDTO;
import com.supermarket.entity.PedidoEntity;
import com.supermarket.entity.ProdutoEntity;
import com.supermarket.repository.ClienteRepository;
import com.supermarket.repository.PedidoRepository;
import com.supermarket.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public List<PedidoEntity> getAll() {
        return pedidoRepository.findAll();
    }

    public PedidoEntity getById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    }

    public PedidoEntity create(PedidoDTO pedidoDTO) {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setCodigo(pedidoDTO.codigo());
        pedido.setCliente(clienteRepository.findById(pedidoDTO.clientId()).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado")));
        List<ProdutoEntity> produtos = produtoRepository.findAllById(pedidoDTO.produtos());
        pedido.setProdutos(produtos);
        return pedidoRepository.save(pedido);
    }

    public PedidoEntity update(PedidoDTO pedidoDTO) {
        PedidoEntity pedido = getById(pedidoDTO.id());
        pedido.setCodigo(pedidoDTO.codigo());
        pedido.setCliente(clienteRepository.findById(pedidoDTO.clientId()).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado")));
        List<ProdutoEntity> produtos = produtoRepository.findAllById(pedidoDTO.produtos());
        pedido.setProdutos(produtos);
        return pedidoRepository.save(pedido);
    }

    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    public void logicDelete(Long id) {
        PedidoEntity pedido = getById(id);
        pedido.setAtivo(false);
        pedidoRepository.save(pedido);
    }
}
