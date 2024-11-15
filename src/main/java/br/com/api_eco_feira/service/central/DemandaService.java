package br.com.api_eco_feira.service.central;

import br.com.api_eco_feira.dto.demanda.DemandaDtoResponse;
import br.com.api_eco_feira.dto.demanda.DemandaResponseUnique;
import br.com.api_eco_feira.enumerador.StatusDemanda;
import br.com.api_eco_feira.model.central.Demanda;
import br.com.api_eco_feira.model.central.DemandaAssociaProdutor;
import br.com.api_eco_feira.model.prefeitura.Prefeitura;
import br.com.api_eco_feira.repository.central.DemandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<DemandaDtoResponse> getFechada(Prefeitura prefeitura) {
        Sort sort = Sort.by(Sort.Direction.DESC, "idDemanda");
        StatusDemanda statusAberta = StatusDemanda.CONCLUIDA;
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

    public DemandaResponseUnique getById(Long id) {
        Demanda demanda = demandaRepository.findById(id).orElse(null);
        if (demanda == null) {
            return null;
        }
        DemandaResponseUnique dru = new DemandaResponseUnique();
        dru.setId(demanda.getIdDemanda());
        dru.setStatusDemanda(demanda.getStatusDemanda());
        dru.setPrazoMaximo(demanda.getPrazoMaximo());
        dru.setValorTotalPrefeitura(demanda.getValorTotalPrefeitura());
        dru.setDataCriacao(demanda.getDataCriacao());
        dru.setDescricao(demanda.getDescricao());
        return dru;
    }

    public String put(Demanda demanda) {
        try{
            demandaRepository.save(demanda);
            return "Demanda atualizada";
        }
        catch(Exception e){
            return "Erro aconteceu";
        }
    }

    public String putCancelado(Demanda demanda) {
        try{
            demandaRepository.save(demanda);
            return "Sucesso";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public List<DemandaDtoResponse> getDemandasByIbge(int ibge) {
        StatusDemanda statusDemanda = StatusDemanda.ABERTA;
        List<Demanda> demandas = demandaRepository.findAllByStatusDemandaAndPrefeituraEnderecoCidadeIbge(statusDemanda, ibge);

        if (demandas.isEmpty()) {
            return Collections.emptyList();
        }

        List<DemandaDtoResponse> demandaDtoResponses = new ArrayList<>();

        for (Demanda demanda : demandas) {
            DemandaDtoResponse ddr = new DemandaDtoResponse();
            ddr.setIdDemanda(demanda.getIdDemanda());
            ddr.setDescricao(demanda.getDescricao());
            ddr.setStatusDemanda(demanda.getStatusDemanda());
            ddr.setPrazoMaximo(demanda.getPrazoMaximo());
            ddr.setValorTotalPrefeitura(demanda.getValorTotalPrefeitura());

            demandaDtoResponses.add(ddr);
        }

        return demandaDtoResponses;
    }

    public List<DemandaDtoResponse> getDemandasAssociadas(List<DemandaAssociaProdutor> demandaAssociaProdutors) {
        return demandaAssociaProdutors.stream()
                .map(DemandaAssociaProdutor::getDemanda)
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
