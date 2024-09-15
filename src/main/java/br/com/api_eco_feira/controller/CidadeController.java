package br.com.api_eco_feira.controller;

import br.com.api_eco_feira.dto.cidade.CidadeResponse;
import br.com.api_eco_feira.service.CidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cidade")
@CrossOrigin("*")
@Tag(name = "Cidade", description = "URI expostos para as cidades do endereço")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/get")
    @Operation(summary = "Busca de cidades",
            description = "Encontra as cidades salvos no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Cidades não encontrados",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<CidadeResponse>> get(@RequestParam String query, @RequestParam Long idEstado){
        List<CidadeResponse> cidadeResponses = cidadeService.get(query, idEstado);
        if(cidadeResponses.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cidadeResponses);

    }


}
