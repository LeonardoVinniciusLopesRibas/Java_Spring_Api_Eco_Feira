package br.com.api_eco_feira.service.central;

import br.com.api_eco_feira.dto.demanda.DemandaDtoResponse;
import br.com.api_eco_feira.model.central.Demanda;
import br.com.api_eco_feira.model.prefeitura.Prefeitura;
import br.com.api_eco_feira.repository.central.DemandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<DemandaDtoResponse> get(Prefeitura prefeitura) {
        Sort sort = Sort.by(Sort.Direction.DESC, "idDemanda");
        List<Demanda> demandas = demandaRepository.findByPrefeitura(prefeitura, sort);
        DemandaDtoResponse ddr = new DemandaDtoResponse();

        return demandas.stream()
                .map(demanda -> new DemandaDtoResponse(
                        demanda.getIdDemanda(),
                        demanda.getDescricao(),
                        demanda.getValorTotalPrefeitura(),
                        demanda.getPrazoMaximo(),
                        demanda.getStatusDemanda()
                ))
                .collect(Collectors.toList());
    }
}
