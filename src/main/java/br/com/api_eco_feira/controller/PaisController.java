package br.com.api_eco_feira.controller;

import br.com.api_eco_feira.dto.pais.PaisResponse;
import br.com.api_eco_feira.service.PaisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pais")
@CrossOrigin("*")
@Tag(name = "País", description = "URI expostos para os paises do endereço")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping("/get")
    @Operation(summary = "Busca de países",
            description = "Encontra os países salvos no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Países não encontrados",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<PaisResponse>> get(@RequestParam String query){
        List<PaisResponse> paisResponses = paisService.get(query);
        if(paisResponses.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paisResponses);
    }

}
