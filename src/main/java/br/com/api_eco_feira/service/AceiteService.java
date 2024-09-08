package br.com.api_eco_feira.service;

import br.com.api_eco_feira.model.Aceite;
import br.com.api_eco_feira.repository.AceiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AceiteService {

    @Autowired
    private AceiteRepository aceiteRepository;

    public String post(Aceite aceite) {
        try{
            aceiteRepository.save(aceite);
            return "Sucesso ao enviar usuário";
        }
        catch(Exception e){
            return "Erro ao enviar usuário"+aceite.getEmail();
        }

    }
}
