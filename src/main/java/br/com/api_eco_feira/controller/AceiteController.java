package br.com.api_eco_feira.controller;

import br.com.api_eco_feira.model.Aceite;
import br.com.api_eco_feira.service.AceiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aceite")
public class AceiteController {

    @Autowired
    private AceiteService aceiteService;


    @PostMapping("/novoacesso")
    @Operation(summary = "URI que tornará um usuário fidedigno de uso",
            description = "Essa URI tornará o usuário fidedigno para uso do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário enviado para análise",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao enviar usuário",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> post(@RequestBody @Valid Aceite aceite) {
        String retorno = aceiteService.post(aceite);

        if(retorno.startsWith("Erro")){
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);

    }

    @GetMapping("/get")
    @Operation(summary = "URI para pegar os usuários pelo CNPJ",
            description = "Essa URI tornará o usuário fidedigno para uso do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários recuperados com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao enviar usuário",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não encontrados",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Aceite>> get(@RequestParam String cnpj){
        List<Aceite> aceites = aceiteService.get(cnpj);

        if(aceites.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aceites);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "URI para buscar os usuários pelo id",
            description = "Essa URI recuperar os usuários pelo id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao deletar o usuário",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Aceite> getId(@PathVariable Long id){
        Aceite aceite = aceiteService.getId(id);
        return ResponseEntity.ok(aceite);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "URI para deletar os usuários pelo id",
            description = "Essa URI recusará os usuários de acesso ao sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao deletar o usuário",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> delete(@PathVariable Long id){
        String retorno = aceiteService.delete(id);
        if(retorno.startsWith("Erro")){
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);
    }

}
