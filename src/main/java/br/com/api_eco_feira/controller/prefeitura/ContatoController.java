package br.com.api_eco_feira.controller.prefeitura;

import br.com.api_eco_feira.dto.contato.ContatoRequest;
import br.com.api_eco_feira.dto.contato.ContatoResponseList;
import br.com.api_eco_feira.model.prefeitura.Contato;
import br.com.api_eco_feira.service.prefeitura.ContatoService;
import br.com.api_eco_feira.service.prefeitura.PrefeituraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contato")
@CrossOrigin("*")
@Tag(name = "Contato", description = "URI expostos para contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private PrefeituraService prefeituraService;

    @PostMapping("/post/{id}")
    @Operation(summary = "URI que da um post de contato",
            description = "Essa URI adicionará um post de contato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato salvo",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao salvar contato",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> post(@RequestBody @Valid ContatoRequest contatoRequest, @PathVariable Long id) {
        Contato contato = new Contato();
        contato.setAtivo(true);
        contato.setDescricaoContato(contatoRequest.getDescricaoContato());
        contato.setEmail(contatoRequest.getEmail());
        contato.setPrefeitura(prefeituraService.getId(id));
        contato.setNumeroTelefone(contatoRequest.getNumeroTelefone());
        String retorno = contatoService.post(contato);

        if(retorno.startsWith("Erro")){
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("/get")
    @Operation(summary = "URI que da um get de contato",
            description = "Essa URI buscará os contatos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato resgatado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Lista de contatos está vazia",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<ContatoResponseList>> get(@RequestParam String cnpj){
        List<ContatoResponseList> contatoResponseLists = contatoService.get(cnpj);
        if(contatoResponseLists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contatoResponseLists);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletar um contato",
            description = "Realiza a busca e deleta o contato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato deletado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao deletar esse contato",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String resposta = contatoService.delete(id);

        if(resposta.startsWith("Erro")){
            return ResponseEntity.badRequest().body(resposta);
        }
        return ResponseEntity.ok(resposta);
    }

}
