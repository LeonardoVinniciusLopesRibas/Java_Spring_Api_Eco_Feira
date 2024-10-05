package br.com.api_eco_feira.service.central;

import br.com.api_eco_feira.model.central.Demanda;
import br.com.api_eco_feira.repository.central.DemandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandaService {

    @Autowired
    private DemandaRepository demandaRepository;


    public String novaDemanda(Demanda demanda) {
        try{
            demandaRepository.save(demanda);
            return "Nova demanda cadastrada";
        }catch (Exception e){
            return "Erro ao cadastrar novo demanda";
        }
    }

    public Demanda getId(Long id){
        return demandaRepository.findById(id).orElse(null);
    }
}
