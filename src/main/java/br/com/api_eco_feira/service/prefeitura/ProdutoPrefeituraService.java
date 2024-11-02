package br.com.api_eco_feira.service.prefeitura;

import br.com.api_eco_feira.dto.produtoprefeitura.ProdutoResponseList;
import br.com.api_eco_feira.model.prefeitura.ProdutoPrefeitura;
import br.com.api_eco_feira.repository.prefeitura.ProdutoPrefeituraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoPrefeituraService {

    @Autowired
    private ProdutoPrefeituraRepository produtoPrefeituraRepository;

    public String post(ProdutoPrefeitura produtoPrefeitura) {

        try{
            produtoPrefeituraRepository.save(produtoPrefeitura);
            return "Produto salvo com sucesso";
        }catch (Exception e){
            return "Erro ao salvar";
        }

    }

    public List<ProdutoResponseList> get(String query, String cnpj) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        List<ProdutoPrefeitura> produtoPrefeituras = produtoPrefeituraRepository.findProdutoPrefeituraByAtivoTrue(sort);

        String lowerCaseQuery = query.toLowerCase();
        String lowerCaseCnpj = cnpj.toLowerCase();

        return produtoPrefeituras.stream()
                .filter(produtoPrefeituraResponse -> {
                    boolean matches = (produtoPrefeituraResponse.getNome().toLowerCase().contains(lowerCaseQuery))&&
                            produtoPrefeituraResponse.getPrefeitura().getCnpj().toLowerCase().contains(lowerCaseCnpj);
                    return matches;
                })
                .map(produtoPrefeituraResponse -> new ProdutoResponseList(
                        produtoPrefeituraResponse.getIdProduto(),
                        produtoPrefeituraResponse.getNome(),
                        produtoPrefeituraResponse.getValorCompra()
                )).collect(Collectors.toList());

    }

    public List<ProdutoResponseList> getDesativados(String query, String cnpj) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        List<ProdutoPrefeitura> produtoPrefeituras = produtoPrefeituraRepository.findProdutoPrefeituraByAtivoFalse(sort);
        String lowerCaseQuery = query.toLowerCase();
        String lowerCaseCnpj = cnpj.toLowerCase();

        return produtoPrefeituras.stream()
                .filter(produtoPrefeitura -> {
                    boolean matches = (produtoPrefeitura.getNome().toLowerCase().contains(lowerCaseQuery))&&
                            produtoPrefeitura.getPrefeitura().getCnpj().toLowerCase().contains(lowerCaseCnpj);
                    return matches;
                })
                .map(produtoPrefeitura -> new ProdutoResponseList(
                        produtoPrefeitura.getIdProduto(),
                        produtoPrefeitura.getNome(),
                        produtoPrefeitura.getValorCompra()
                ))
                .collect(Collectors.toList());
    }

    public ProdutoPrefeitura getId(Long id) {
        Optional<ProdutoPrefeitura> optionalProdutoPrefeitura = produtoPrefeituraRepository.findById(id);
        return optionalProdutoPrefeitura.orElse(null);
    }

    public String put(ProdutoPrefeitura produtoPrefeitura) {
        try{
            produtoPrefeituraRepository.save(produtoPrefeitura);
            return "Produto salvo com sucesso";
        }
        catch (Exception e){
            return "Erro ao salvar";
        }
    }

    public String putAtivo(Boolean ativo, Long id) {
        ProdutoPrefeitura produtoPrefeitura = getId(id);
        if(produtoPrefeitura == null){
            return "Erro ao deixar o estado: "+ativo;
        }
        produtoPrefeitura.setAtivo(ativo);
        produtoPrefeituraRepository.save(produtoPrefeitura);
        return "Produto salvo com sucesso";
    }

}
