package com.supermarket.service;

import com.supermarket.entity.ProdutoEntity;
import com.supermarket.repository.ProdutoRepository;
import com.supermarket.dto.ProdutoDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public List<ProdutoEntity> getAll() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity getById(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    public ProdutoEntity create(ProdutoDTO produtoDTO) {
        ProdutoEntity produto = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDTO, produto);
        return produtoRepository.save(produto);
    }

    public ProdutoEntity update(ProdutoDTO produtoDTO) {
        ProdutoEntity produto = getById(produtoDTO.id());
        BeanUtils.copyProperties(produtoDTO, produto);
        return produtoRepository.save(produto);
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    public void logicDelete(Long id) {
        ProdutoEntity produto = getById(id);
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }
}
