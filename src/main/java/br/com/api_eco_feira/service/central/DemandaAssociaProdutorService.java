package br.com.api_eco_feira.service.central;

import br.com.api_eco_feira.model.central.DemandaAssociaProdutor;
import br.com.api_eco_feira.repository.central.DemandaAssociaProdutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandaAssociaProdutorService {

    @Autowired
    private DemandaAssociaProdutorRepository demandaAssociaProdutorRepository;

    public String vincularDemandaAoProdutor(DemandaAssociaProdutor dap) {
        try{
            demandaAssociaProdutorRepository.save(dap);
            return "Sucesso ao vincular";
        }catch (Exception e){
            return "Ocorreu um erro:"+e.getMessage();
        }
    }

    public boolean isDemandaAssociadaAoProdutor(Long idDemanda, Long idProdutor) {
        return demandaAssociaProdutorRepository.existsByDemandaIdDemandaAndEmpresaIdEmpresa(idDemanda, idProdutor);
    }

    public List<DemandaAssociaProdutor> getDados(Long idProdutor){
        return demandaAssociaProdutorRepository.findAllByEmpresaIdEmpresa(idProdutor);
    }
}
