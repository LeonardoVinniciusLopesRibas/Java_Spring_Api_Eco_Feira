package br.com.api_eco_feira.service;

import br.com.api_eco_feira.dto.estado.EstadoResponse;
import br.com.api_eco_feira.model.Estado;
import br.com.api_eco_feira.model.Pais;
import br.com.api_eco_feira.repository.EstadoRepository;
import br.com.api_eco_feira.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;


    public List<EstadoResponse> get(String query, Long idPais) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        Pais pais = paisRepository.findById(idPais).orElse(null);
        List<Estado> estados = estadoRepository.findAllByPais(pais, sort);
        String lowerCaseQuery = query.toLowerCase();

        return estados.stream()
                .filter(estadosResponse -> {
                    boolean matches = estadosResponse.getNome().toLowerCase().contains(lowerCaseQuery);
                    return matches;
                })
                .map(estado -> new EstadoResponse(
                        estado.getIdEstado(),
                        estado.getNome(),
                        estado.getSigla()
                ))
                .collect(Collectors.toList());
    }
}
