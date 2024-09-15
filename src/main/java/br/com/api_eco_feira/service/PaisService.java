package br.com.api_eco_feira.service;

import br.com.api_eco_feira.dto.pais.PaisResponse;
import br.com.api_eco_feira.model.Pais;
import br.com.api_eco_feira.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public List<PaisResponse> get(String query) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        List<Pais> paises = paisRepository.findAll(sort);
        String lowerCaseQuery = query.toLowerCase();

        return paises.stream()
                .filter(paisesResponse -> {
                    boolean matches = paisesResponse.getNome().toLowerCase().contains(lowerCaseQuery);
                    return matches;
                })
                .map(paisesResponse -> new PaisResponse(
                        paisesResponse.getIdPais(),
                        paisesResponse.getNome(),
                        paisesResponse.getSigla()
                ))
                .collect(Collectors.toList());


    }
}
