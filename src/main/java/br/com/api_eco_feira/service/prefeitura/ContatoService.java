package br.com.api_eco_feira.service.prefeitura;

import br.com.api_eco_feira.dto.contato.ContatoRequest;
import br.com.api_eco_feira.dto.contato.ContatoResponseList;
import br.com.api_eco_feira.model.prefeitura.Contato;
import br.com.api_eco_feira.repository.prefeitura.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public String post(Contato contato) {
        try{
            contatoRepository.save(contato);
            return "Contato salvo com sucesso";
        }catch (Exception e){
            return "Erro ao salvar contato";
        }

    }

    public List<ContatoResponseList> get(String cnpj) {

        List<Contato> contatos = contatoRepository.findAll();
        String lowerCaseCnpj = cnpj.toLowerCase();

        return contatos.stream()
                .filter(contatosResponse -> {
                    boolean matches = (contatosResponse.getPrefeitura().getCnpj().toLowerCase().contains(lowerCaseCnpj));
                    return matches;
                })
                .map(contatoResponse -> new ContatoResponseList(
                        contatoResponse.getIdPrefeitura(),
                        contatoResponse.getDescricaoContato(),
                        contatoResponse.getNumeroTelefone(),
                        contatoResponse.getEmail()
                ))
                .collect(Collectors.toList());
    }

    public String delete(Long id) {

        try{
            contatoRepository.deleteById(id);
            return "Contato deletado com sucesso";
        }
        catch (Exception e){
            return "Erro ao deletar contato";
        }
    }
}
