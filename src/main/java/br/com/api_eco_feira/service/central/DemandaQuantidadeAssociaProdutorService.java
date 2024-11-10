package br.com.api_eco_feira.service.central;

import br.com.api_eco_feira.model.central.DemandaQuantidadeAssociaProdutor;
import br.com.api_eco_feira.repository.central.DemandaAssociaProdutorRepository;
import br.com.api_eco_feira.repository.central.DemandaQuantidadeAssociaProdutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandaQuantidadeAssociaProdutorService {

    @Autowired
    private DemandaQuantidadeAssociaProdutorRepository demandaQuantidadeAssociaProdutorRepository;

    public String atendendo (DemandaQuantidadeAssociaProdutor demanda) {
        try{
            demandaQuantidadeAssociaProdutorRepository.save(demanda);
            return "Sucesso";
        }
        catch(Exception e){
            return "Erro";
        }
    }

}
