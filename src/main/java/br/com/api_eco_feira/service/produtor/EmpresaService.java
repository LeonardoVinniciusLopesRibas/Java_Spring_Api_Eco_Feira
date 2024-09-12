package br.com.api_eco_feira.service.produtor;

import br.com.api_eco_feira.model.produtor.Empresa;
import br.com.api_eco_feira.repository.produtor.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa getId(Long empresaId) {
        return empresaRepository.findById(empresaId).orElse(null);
    }

    public boolean getCnpj(String cnpj){
        return empresaRepository.existsByCnpj(cnpj);
    }
}
