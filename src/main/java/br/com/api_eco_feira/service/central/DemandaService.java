package br.com.api_eco_feira.service.central;

import br.com.api_eco_feira.dto.demanda.DemandaDtoResponse;
import br.com.api_eco_feira.enumerador.StatusDemanda;
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


    public Long novaDemanda(Demanda demanda) {
        try {
            Demanda demandaSalva = demandaRepository.save(demanda);
            return demandaSalva.getIdDemanda();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar demanda: " + e.getMessage());
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

    public List<DemandaDtoResponse> getAberta(Prefeitura prefeitura) {
        Sort sort = Sort.by(Sort.Direction.DESC, "idDemanda");
        StatusDemanda statusAberta = StatusDemanda.ABERTA;
        List<Demanda> demandas = demandaRepository.findByPrefeituraAndStatusDemanda(prefeitura, statusAberta, sort);

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
