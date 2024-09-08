package br.com.api_eco_feira.controller;

import br.com.api_eco_feira.auth.Usuario;
import br.com.api_eco_feira.model.Aceite;
import br.com.api_eco_feira.model.produtor.Empresa;
import br.com.api_eco_feira.service.AceiteService;
import br.com.api_eco_feira.service.UsuarioService;
import br.com.api_eco_feira.service.produtor.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin("*")
@Tag(name = "Usuário", description = "URI expostos para as buscas de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AceiteService aceiteService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/get/{id}")
    @Operation(summary = "Busca de usuários",
            description = "Realiza a busca de usuário por id informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários recuperados com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o usuário com id informado",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Usuario> getId(@PathVariable Long id) {
        Usuario usuario = usuarioService.getId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/post/{id}")
    @Operation(summary = "Cadastra um usuário",
            description = "Realiza o cadastro de um novo usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao cadastrar usuário",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não encontrado aceites com id informado",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> post(@PathVariable Long id, @RequestBody Long idempresa) {
        Aceite aceite = aceiteService.getId(id);
        if(aceite == null){
            return ResponseEntity.notFound().build();
        }

        Empresa empresa = empresaService.getId(idempresa);
        if(empresa == null){
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = new Usuario();
        usuario.setId(aceite.getId());
        usuario.setPerfil("admin");
        usuario.setProdutor(true);
        usuario.setSuporte(false);
        usuario.setPrefeitura(false);
        usuario.setEmpresaAssociation(empresa);
        usuario.setPrefeituraAssociation(null);
        usuario.setUsuario(aceite.getEmail());
        usuario.setSenha(aceite.getSenha());
        String retorno = usuarioService.post(usuario);

        if(retorno.startsWith("Erro")){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(retorno);
    }
}
