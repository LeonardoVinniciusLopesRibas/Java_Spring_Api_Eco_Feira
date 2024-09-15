package br.com.api_eco_feira.service.produtor;

import br.com.api_eco_feira.dto.produtoprodutor.ProdutoProdutorRequest;
import br.com.api_eco_feira.dto.produtoprodutor.ProdutoProdutorResponse;
import br.com.api_eco_feira.model.produtor.Empresa;
import br.com.api_eco_feira.model.produtor.ProdutoProdutor;
import br.com.api_eco_feira.repository.produtor.ProdutoProdutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoProdutorService {

    @Autowired
    private ProdutoProdutorRepository produtoProdutorRepository;

    public boolean temProdutoNoGrupo(Long id){
        return produtoProdutorRepository.existsByGrupoProdutos_IdGrupo(id);
    }

    public String post(ProdutoProdutor produtoProdutor) {
        try{

            produtoProdutorRepository.save(produtoProdutor);
            return "Produto salvo com sucesso!";
        }
        catch(Exception e){
            return "Erro ao cadastrar produto!";
        }
    }

    public List<ProdutoProdutorResponse> get(String query, String cnpj) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        List<ProdutoProdutor> produtoProdutors = produtoProdutorRepository.findProdutoProdutorByAtivoTrue(sort);
        String lowerCaseQuery = query.toLowerCase();
        String lowerCaseCnpj = cnpj.toLowerCase();

        return produtoProdutors.stream()
                .filter(produtoProdutor1 ->{
                    boolean matches = (produtoProdutor1.getNome().toLowerCase().contains(lowerCaseQuery) ||
                            produtoProdutor1.getGrupoProdutos().getDescricaoGrupo().toLowerCase().contains(lowerCaseQuery)) &&
                            produtoProdutor1.getEmpresa().getCnpj().toLowerCase().contains(lowerCaseCnpj);
                    return matches;
                })
                .map(produtoProdutor1 -> new ProdutoProdutorResponse(
                        produtoProdutor1.getIdProduto(),
                        produtoProdutor1.getNome(),
                        produtoProdutor1.getValorCusto(),
                        produtoProdutor1.getValorVenda(),
                        produtoProdutor1.getGrupoProdutos().getDescricaoGrupo(),
                        produtoProdutor1.isApareceEmDemandas()
                ))
                .collect(Collectors.toList());
    }

    public List<ProdutoProdutorResponse> getDesativados(String query, String cnpj) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        List<ProdutoProdutor> produtoProdutors = produtoProdutorRepository.findProdutoProdutorByAtivoFalse(sort);
        String lowerCaseQuery = query.toLowerCase();
        String lowerCaseCnpj = cnpj.toLowerCase();

        return produtoProdutors.stream()
                .filter(produtoProdutor1 ->{
                    boolean matches = (produtoProdutor1.getNome().toLowerCase().contains(lowerCaseQuery) ||
                            produtoProdutor1.getGrupoProdutos().getDescricaoGrupo().toLowerCase().contains(lowerCaseQuery)) &&
                            produtoProdutor1.getEmpresa().getCnpj().toLowerCase().contains(lowerCaseCnpj);
                    return matches;
                })
                .map(produtoProdutor1 -> new ProdutoProdutorResponse(
                        produtoProdutor1.getIdProduto(),
                        produtoProdutor1.getNome(),
                        produtoProdutor1.getValorCusto(),
                        produtoProdutor1.getValorVenda(),
                        produtoProdutor1.getGrupoProdutos().getDescricaoGrupo(),
                        produtoProdutor1.isApareceEmDemandas()
                ))
                .collect(Collectors.toList());
    }

    public ProdutoProdutor getId(Long id) {
        Optional<ProdutoProdutor> optionalProdutoProdutor = produtoProdutorRepository.findById(id);
        return optionalProdutoProdutor.orElse(null);
    }

    public String put(ProdutoProdutor produtoProdutor, Long id) {
        try{
            produtoProdutorRepository.save(produtoProdutor);
            return "Produto salvo com sucesso!";
        }
        catch(Exception e){
            return "Erro ao atualizar produto!";
        }
    }

    public String putAtivo(boolean ativo, Long id) {
        ProdutoProdutor produtoProdutor = getId(id);
        if(produtoProdutor == null){
            return "Erro para deixar o estado: "+ativo;
        }
        produtoProdutor.setAtivo(ativo);
        produtoProdutorRepository.save(produtoProdutor);
        return "Produto salvo com sucesso!";
    }
}

