package br.com.api_eco_feira.controller;

import br.com.api_eco_feira.dto.estado.EstadoResponse;
import br.com.api_eco_feira.service.EstadoService;
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
@RequestMapping("/api/estado")
@CrossOrigin("*")
@Tag(name = "Estado", description = "URI expostos para os estados do endereço")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/get")
    @Operation(summary = "Busca de estados",
            description = "Encontra os estados salvos no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Estados não encontrados",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<EstadoResponse>> get(@RequestParam String query, @RequestParam Long idPais){
        List<EstadoResponse> estadoResponses = estadoService.get(query, idPais);
        if(estadoResponses.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estadoResponses);
    }

}
