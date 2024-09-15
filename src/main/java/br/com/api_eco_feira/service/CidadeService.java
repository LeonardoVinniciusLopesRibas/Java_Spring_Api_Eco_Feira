package br.com.api_eco_feira.service;

import br.com.api_eco_feira.dto.cidade.CidadeResponse;
import br.com.api_eco_feira.model.Cidade;
import br.com.api_eco_feira.model.Estado;
import br.com.api_eco_feira.repository.CidadeRepository;
import br.com.api_eco_feira.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;


    public List<CidadeResponse> get(String query, Long idEstado) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        Estado estado = estadoRepository.findById(idEstado).orElse(null);
        List<Cidade> cidades = cidadeRepository.findAllByEstado(estado, sort);
        String lowerCaseQuery = query.toLowerCase();

        return cidades.stream()
                .filter(cidadeResponse -> {
                    boolean matches = cidadeResponse.getNome().toLowerCase().contains(lowerCaseQuery);
                    return matches;
                })
                .map(cidadeResponse -> new CidadeResponse(
                        cidadeResponse.getIdCidade(),
                        cidadeResponse.getNome()
                ))
                .collect(Collectors.toList());
    }

    public Cidade getId(Long id){
        return cidadeRepository.findById(id).orElse(null);
    }
}
