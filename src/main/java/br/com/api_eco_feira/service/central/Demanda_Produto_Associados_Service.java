package br.com.api_eco_feira.service.central;

import br.com.api_eco_feira.repository.central.Demanda_Produto_Associados_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Demanda_Produto_Associados_Service {

    @Autowired
    private Demanda_Produto_Associados_Repository demanda_produto_associados_repository;

}
