package br.com.api_eco_feira.service.central;

import br.com.api_eco_feira.model.central.Demanda_Produto_Associados;
import br.com.api_eco_feira.repository.central.Demanda_Produto_Associados_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Demanda_Produto_Associados_Service {

    @Autowired
    private Demanda_Produto_Associados_Repository demanda_produto_associados_repository;

    public void novoProdutoPrefeituraDemanda(Demanda_Produto_Associados dpa) {
        try {
            demanda_produto_associados_repository.save(dpa);
            System.out.println("Produto associado com sucesso: " + dpa);
        } catch (Exception e) {
            System.err.println("Erro ao salvar produto associado: " + e.getMessage());
            throw e;
        }
    }

}
