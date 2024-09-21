package br.com.api_eco_feira.service.prefeitura;

import br.com.api_eco_feira.model.prefeitura.Prefeitura;
import br.com.api_eco_feira.repository.prefeitura.PrefeituraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrefeituraService {

    @Autowired
    private PrefeituraRepository prefeituraRepository;

    public Prefeitura getId(Long id){
        return prefeituraRepository.findById(id).orElse(null);
    }

    public boolean getCnpj(String cnpj){
        return prefeituraRepository.existsByCnpj(cnpj);
    }

}
