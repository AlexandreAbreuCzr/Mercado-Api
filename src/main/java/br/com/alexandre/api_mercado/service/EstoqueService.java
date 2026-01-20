package br.com.alexandre.api_mercado.service;

import br.com.alexandre.api_mercado.dto.EstoqueCreateDTO;
import br.com.alexandre.api_mercado.dto.EstoqueResponseDTO;
import br.com.alexandre.api_mercado.dto.EstoqueUpdateDTO;
import br.com.alexandre.api_mercado.dto.ProdutoResponseDTO;
import br.com.alexandre.api_mercado.exeptions.geral.PreencherCamposException;
import br.com.alexandre.api_mercado.exeptions.geral.ProdutoComEstoqueException;
import br.com.alexandre.api_mercado.exeptions.not_found.EstoqueNotFoundException;
import br.com.alexandre.api_mercado.exeptions.not_found.ProdutoNotFoundException;
import br.com.alexandre.api_mercado.model.Estoque;
import br.com.alexandre.api_mercado.model.Produto;
import br.com.alexandre.api_mercado.repository.EstoqueRepository;
import br.com.alexandre.api_mercado.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public EstoqueResponseDTO getEstoqueByProductName(String productName){
        List<Estoque> estoqueList = estoqueRepository.findAll();
        for (Estoque estoque : estoqueList){
            if(estoque.getProduct().getName().equals(productName)){
                return mapEstoque(estoque);
            }
        }
        throw new RuntimeException("Estoque não encontrado");
    }

    public EstoqueResponseDTO save(EstoqueCreateDTO dto){
        if (dto.produtoId() == null || dto.quantity() == null){
            throw new IllegalArgumentException("Preencher Todos os campos");
        }

        Estoque estoque = new Estoque();
        estoque.setProduct(produtoRepository.findById(dto.produtoId()).orElseThrow(
                ProdutoNotFoundException::new
        ));

        estoque.setQuantity(dto.quantity());

        Estoque salvo = estoqueRepository.save(estoque);
        return mapEstoque(salvo);
    }

    public List<EstoqueResponseDTO> getAll(){
        return estoqueRepository.findAll().stream()
                .map(this::mapEstoque)
                .toList();
    }

    private EstoqueResponseDTO mapEstoque(Estoque estoque){
        ProdutoResponseDTO produtoDTO  = new ProdutoResponseDTO(
                estoque.getProduct().getId(),
                estoque.getProduct().getName(),
                estoque.getProduct().getPrice(),
                estoque.getProduct().getData_created(),
                estoque.getProduct().getCategoria().getId()
        );
        EstoqueResponseDTO response = new EstoqueResponseDTO(
                estoque.getId(),
                produtoDTO,
                estoque.getQuantity(),
                estoque.getLastUpdate()
        );
        return response;
    }

    public EstoqueResponseDTO update(Long estoqueId, EstoqueUpdateDTO dto){
        if(dto.quantity() == null){
            throw new PreencherCamposException();
        }

        Estoque estoque = estoqueRepository.findById(estoqueId)
                .orElseThrow(EstoqueNotFoundException::new);

        estoque.setQuantity(dto.quantity());
        return mapEstoque(estoqueRepository.save(estoque));
    }

    public EstoqueResponseDTO updateByProduto(Long produtoId, Integer novaQuantidade){
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(EstoqueNotFoundException::new);

        Estoque estoque = estoqueRepository.findByProduct(produto)
                .orElseThrow(EstoqueNotFoundException::new);

        estoque.setQuantity(novaQuantidade);
        return mapEstoque(estoqueRepository.save(estoque));
    }

    public EstoqueResponseDTO findById(Long id){
        Estoque estoque = estoqueRepository.findById(id).orElseThrow(
                EstoqueNotFoundException::new
        );
        return mapEstoque(estoque);
    }

    public EstoqueResponseDTO findByProductId(Long id){
        List<Estoque> estoqueList = estoqueRepository.findAll();

        for (Estoque estoque: estoqueList){
            if (estoque.getProduct().getId() == id){
                return mapEstoque(estoque);
            }
        }
        throw new EstoqueNotFoundException();
    }
    @Transactional
    public void delete(Long id) {

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(ProdutoNotFoundException::new);

        if (estoque.getQuantity() > 0) {
            throw new ProdutoComEstoqueException();
        }

        estoqueRepository.delete(estoque);
    }


}
