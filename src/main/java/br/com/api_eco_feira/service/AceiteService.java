package br.com.api_eco_feira.service;

import br.com.api_eco_feira.model.Aceite;
import br.com.api_eco_feira.repository.AceiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AceiteService {

    @Autowired
    private AceiteRepository aceiteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String post(Aceite aceite) {
        if (validaEmail(aceite.getEmail())) {
            try {
                aceite.setSenha(passwordEncoder.encode(aceite.getSenha()));
                aceiteRepository.save(aceite);
                return "Sucesso ao enviar usuário";
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao enviar usuário: " + aceite.getEmail();
            }
        } else {
            return "Erro. E-mail já cadastrado";
        }
    }

    public boolean validaEmail(String email) {
        return !aceiteRepository.existsByEmail(email);
    }

    public List<Aceite> get(String cnpj) {
        List<Aceite> aceites = new ArrayList<>();
        aceites = aceiteRepository.findAceiteByCnpj(cnpj);
        return aceites;
    }

    public String delete(Long id) {
        try{
            aceiteRepository.deleteById(id);
            return "Sucesso ao deletar usuário!";
        }catch(Exception e){
            return "Erro ao deletar usuário: "+id;
        }
    }

    public Aceite getId(Long id) {
        Optional<Aceite> aceiteOptional = aceiteRepository.findById(id);
        return aceiteOptional.orElse(null);
    }
}
