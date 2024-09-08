package br.com.api_eco_feira.controller;

import br.com.api_eco_feira.model.Aceite;
import br.com.api_eco_feira.service.AceiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/usuario")
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
    public ResponseEntity<String> post(@RequestBody Aceite aceite) {
        String retorno = aceiteService.post(aceite);

        if(retorno.startsWith("Erro")){
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);

    }
}
