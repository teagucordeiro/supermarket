package com.supermarket.controller;

import com.supermarket.dto.ProdutoDTO;
import com.supermarket.entity.ProdutoEntity;
import com.supermarket.entity.GeneroProduto;
import com.supermarket.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<ProdutoEntity> getAll() {
        return produtoRepository.findAll().stream().filter(ProdutoEntity::isAtivo).toList();
    }

    @GetMapping("/{id}")
    public ProdutoEntity getById(@PathVariable Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ProdutoEntity postProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoEntity produto = new ProdutoEntity(null, produtoDTO.nomeProduto(), produtoDTO.marca(), produtoDTO.dataFabricacao(), produtoDTO.dataValidade(), GeneroProduto.valueOf(produtoDTO.genero()), produtoDTO.lote(), true);
        return produtoRepository.save(produto);
    }

    @PutMapping
    public ProdutoEntity putProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoEntity produto = produtoRepository.findById(produtoDTO.id()).orElse(null);
        if (produto != null && produto.isAtivo()) {
            produto.setNomeProduto(produtoDTO.nomeProduto());
            produto.setMarca(produtoDTO.marca());
            produto.setDataFabricacao(produtoDTO.dataFabricacao());
            produto.setDataValidade(produtoDTO.dataValidade());
            produto.setGenero(GeneroProduto.valueOf(produtoDTO.genero()));
            produto.setLote(produtoDTO.lote());
            return produtoRepository.save(produto);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }

    @DeleteMapping("/logic/{id}")
    public void deleteLogic(@PathVariable Long id) {
        ProdutoEntity produto = produtoRepository.findById(id).orElse(null);
        if (produto != null) {
            produto.setAtivo(false);
            produtoRepository.save(produto);
        }
    }
}
