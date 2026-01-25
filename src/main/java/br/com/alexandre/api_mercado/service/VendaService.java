package br.com.alexandre.api_mercado.service;

import br.com.alexandre.api_mercado.dto.VendaCreateDTO;
import br.com.alexandre.api_mercado.dto.VendaItemCreateDTO;
import br.com.alexandre.api_mercado.dto.VendaItemResponseDTO;
import br.com.alexandre.api_mercado.dto.VendaResponseDTO;
import br.com.alexandre.api_mercado.exeptions.geral.PreencherCamposException;
import br.com.alexandre.api_mercado.exeptions.not_found.ProdutoNotFoundException;
import br.com.alexandre.api_mercado.exeptions.not_found.VendaNotFoundException;
import br.com.alexandre.api_mercado.model.Produto;
import br.com.alexandre.api_mercado.model.Venda;
import br.com.alexandre.api_mercado.model.VendaItem;
import br.com.alexandre.api_mercado.repository.ProdutoRepository;
import br.com.alexandre.api_mercado.repository.VendaItemRepository;
import br.com.alexandre.api_mercado.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendaItemRepository vendaItemRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueService estoqueService;


    public VendaResponseDTO criarVenda(VendaCreateDTO dto){
        if (dto.items() == null || dto.items().isEmpty()){
            throw new PreencherCamposException();
        }

        Venda venda = new Venda();
        List<VendaItem> vendaItems = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        for (VendaItemCreateDTO itemDTO : dto.items()){
            Produto produto = produtoRepository.findById(itemDTO.productId())
                    .orElseThrow(ProdutoNotFoundException::new);

            estoqueService.validarEstoque(itemDTO.productId(), itemDTO.quantity());
            estoqueService.baixarEstoque(itemDTO.productId(), itemDTO.quantity());

            BigDecimal subTotal =
                    produto.getPrice().multiply(BigDecimal.valueOf(itemDTO.quantity()));

            VendaItem item = new VendaItem();
            item.setVenda(venda);
            item.setProduto(produto);
            item.setQuantity(itemDTO.quantity());
            item.setPrice(produto.getPrice());

            vendaItems.add(item);
            total = total.add(subTotal);
        }
        venda.setItems(vendaItems);
        venda.setTotalPrice(total);

        Venda vendaSalva = vendaRepository.save(venda);

        return mapVenda(vendaSalva);



    }


    public VendaResponseDTO findVendaById(Long id){
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(VendaNotFoundException::new);

        return mapVenda(venda);
    }


    public List<VendaResponseDTO> getAll(){
        return vendaRepository.findAll().stream()
                .map(this::mapVenda)
                .toList();
    }

    public List<VendaResponseDTO> findByProductName(String name) {
        return vendaItemRepository.findVendasByProductName(name)
                .stream()
                .map(this::mapVenda)
                .toList();
    }



    private VendaItemResponseDTO mapVendaItem(VendaItem vendaItem){
        return new VendaItemResponseDTO(
                vendaItem.getId(),
                vendaItem.getProduto().getId(),
                vendaItem.getProduto().getName(),
                vendaItem.getQuantity(),
                vendaItem.getPrice(),
                vendaItem.getPrice().multiply(BigDecimal.valueOf(vendaItem.getQuantity()))
        );
    }

    private VendaResponseDTO mapVenda(Venda venda){
        List<VendaItemResponseDTO> vendaItemResponseDTOS = venda.getItems().stream().map(this::mapVendaItem).toList();
        return new VendaResponseDTO(
                venda.getId(),
                vendaItemResponseDTOS,
                venda.getTotalPrice(),
                venda.getCreatedAt(),
                venda.getLastUpdate()
        );
    }



}
