package br.com.api_eco_feira.service;

import br.com.api_eco_feira.auth.Usuario;
import br.com.api_eco_feira.model.produtor.Empresa;
import br.com.api_eco_feira.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getId(Long empresaId) {
        return usuarioRepository.findById(empresaId).orElse(null);
    }

    public String post(Usuario usuario) {
        try{
            usuarioRepository.save(usuario);
            return "Sucesso ao cadastrar usuário";
        }catch (Exception e){
            return "Erro ao cadastrar usuario";
        }

    }
}
