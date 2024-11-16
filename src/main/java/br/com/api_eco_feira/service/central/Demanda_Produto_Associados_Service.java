package br.com.api_eco_feira.service.central;

import br.com.api_eco_feira.dto.demandaprodutosassociados.DemandaProdutoResponse;
import br.com.api_eco_feira.model.central.Demanda;
import br.com.api_eco_feira.model.central.Demanda_Produto_Associados;
import br.com.api_eco_feira.repository.central.DemandaRepository;
import br.com.api_eco_feira.repository.central.Demanda_Produto_Associados_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Demanda_Produto_Associados_Service {

    @Autowired
    private Demanda_Produto_Associados_Repository demanda_produto_associados_repository;

    @Autowired
    private DemandaRepository demandaRepository;

    public void novoProdutoPrefeituraDemanda(Demanda_Produto_Associados dpa) {
        try {
            demanda_produto_associados_repository.save(dpa);
            System.out.println("Produto associado com sucesso: " + dpa);
        } catch (Exception e) {
            System.err.println("Erro ao salvar produto associado: " + e.getMessage());
            throw e;
        }
    }

    public List<DemandaProdutoResponse> getProdutos(Long idDemanda) {
        Demanda demanda = demandaRepository.findById(idDemanda).orElse(null);
        List<Demanda_Produto_Associados> produtosDemandas =
                demanda_produto_associados_repository.findAllByDemandas(demanda);
        return produtosDemandas.stream().map(produto -> {
            DemandaProdutoResponse response = new DemandaProdutoResponse();
            response.setIdDemandaProduto(produto.getIdDemandaProduto());
            response.setProdutoPrefeitura(produto.getProdutoPrefeitura().getNome());
            response.setQuantidade(produto.getQuantidade());
            response.setValorPrefeitura(produto.getValorPrefeitura());
            response.setUnidadeMedida(produto.getProdutoPrefeitura().getUnidadeMedida());
            double saldo = produto.getSaldo();
            double quantidade = produto.getQuantidade();
            double percentual = (quantidade > 0) ? Math.round((double) saldo / quantidade * 100 * 100) / 100.0 : 0.0;
            response.setSaldo(percentual);
            return response;
        }).collect(Collectors.toList());
    }

    public Demanda_Produto_Associados findById(Long idDemandaProdutoAssociados){
        return demanda_produto_associados_repository.findById(idDemandaProdutoAssociados).orElse(null);
    }

    public String atualizar(Demanda_Produto_Associados demandaProdutoAssociados){
        demanda_produto_associados_repository.save(demandaProdutoAssociados);
        return "Sucesso";
    }
}
