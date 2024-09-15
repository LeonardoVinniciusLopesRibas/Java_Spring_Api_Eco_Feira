package br.com.api_eco_feira.service;

import br.com.api_eco_feira.model.Endereco;
import br.com.api_eco_feira.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco getId(Long id){
        return enderecoRepository.findById(id).orElse(null);
    }

    public String put(Endereco endereco) {
        try{
            enderecoRepository.save(endereco);
            return "Sucesso ao atualizar";
        }catch (Exception e){
            return "Erro ao atualizar";
        }
    }
}
