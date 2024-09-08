package br.com.api_eco_feira.service.produtor;

import br.com.api_eco_feira.model.produtor.ProdutoProdutor;
import br.com.api_eco_feira.repository.produtor.ProdutoProdutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
