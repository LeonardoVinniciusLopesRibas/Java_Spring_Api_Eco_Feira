package br.com.api_eco_feira.service.produtor;

import br.com.api_eco_feira.auth.Usuario;
import br.com.api_eco_feira.controller.produtor.EmpresaController;
import br.com.api_eco_feira.dto.GrupoProdutosRequest;
import br.com.api_eco_feira.dto.GrupoProdutosResponse;
import br.com.api_eco_feira.model.produtor.Empresa;
import br.com.api_eco_feira.model.produtor.GrupoProdutos;
import br.com.api_eco_feira.repository.produtor.GrupoProdutosRepository;
import br.com.api_eco_feira.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GrupoProdutosService {

    @Autowired
    private GrupoProdutosRepository grupoProdutosRepository;

    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ProdutoProdutorService produtoProdutorService;

    public String novoGrupo(GrupoProdutosRequest grupoProdutosRequest) {

        GrupoProdutos grupoProdutos = new GrupoProdutos();
        grupoProdutos.setDescricaoGrupo(grupoProdutosRequest.getDescricaoGrupo());
        grupoProdutos.setAtivo(true);
        Empresa empresa = empresaService.getId(grupoProdutosRequest.getEmpresaId());
        if (empresa == null) {
            return "Erro: Empresa não encontrada para o ID fornecido.";
        }
        grupoProdutos.setEmpresa(empresa);

        Usuario usuario = usuarioService.getId(grupoProdutosRequest.getUsuarioId());
        if (usuario == null) {
            return "Erro: Usuário não encontrado para o ID fornecido.";
        }
        grupoProdutos.setUsuario(usuario);

        try {
            grupoProdutosRepository.save(grupoProdutos);
            return "Sucesso ao cadastrar grupo: " + grupoProdutos.getDescricaoGrupo();
        } catch (Exception e) {
            return "Erro ao cadastrar grupo: " + grupoProdutos.getDescricaoGrupo();
        }
    }

    public List<GrupoProdutosResponse> get(String query) {
        Sort sort = Sort.by(Sort.Direction.ASC, "descricaoGrupo");
        List<GrupoProdutos> grupoProdutos = grupoProdutosRepository.findAll(sort);
        String lowerCaseQuery = query.toLowerCase();


        return grupoProdutos.stream()
                .filter(grupoProdutos1 -> {
                    boolean matches = grupoProdutos1.getDescricaoGrupo().toLowerCase().contains(lowerCaseQuery) ||
                            grupoProdutos1.getUsuario().getUsuario().toLowerCase().contains(lowerCaseQuery);
                    return matches;
                })
                .map(grupoProdutos1 -> new GrupoProdutosResponse(
                        grupoProdutos1.getIdGrupo(),
                        grupoProdutos1.getDescricaoGrupo(),
                        grupoProdutos1.getUsuario().getUsuario()
                ))
                .collect(Collectors.toList());
    }

    public GrupoProdutos getById(Long id) {
        Optional<GrupoProdutos> grupoProdutos = grupoProdutosRepository.findById(id);
        return grupoProdutos.orElse(null);
    }

    public String delete(Long id) {

        boolean tem = produtoProdutorService.temProdutoNoGrupo(id);
        if (tem) {
            return "Existem produtos vinculados a esse grupo com código: " + id;
        }

        GrupoProdutos grupoProdutos = getById(id);
        if (grupoProdutos == null) {
            return "Não";
        }

        grupoProdutosRepository.deleteById(id);
        return "Grupo deletado com sucesso";

    }
}
