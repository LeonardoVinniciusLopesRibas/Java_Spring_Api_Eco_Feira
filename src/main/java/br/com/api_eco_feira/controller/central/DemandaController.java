package br.com.api_eco_feira.controller.central;

import br.com.api_eco_feira.dto.demanda.DemandaDtoRequest;
import br.com.api_eco_feira.enumerador.StatusDemanda;
import br.com.api_eco_feira.model.central.Demanda;
import br.com.api_eco_feira.model.prefeitura.Prefeitura;
import br.com.api_eco_feira.service.central.DemandaService;
import br.com.api_eco_feira.service.prefeitura.PrefeituraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demanda")
@CrossOrigin("*")
@Tag(name = "Demanda", description = "URI expostos para demanda")
public class DemandaController {

    @Autowired
    private DemandaService demandaService;

    @Autowired
    private PrefeituraService prefeituraService;

    @PostMapping("/nova")
    @Operation(summary = "uri para nova demanda",
            description = "essa uri serve para dar post em uma demanda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nova demanda",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao gerar uma demanda",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> novaDemanda (@RequestBody DemandaDtoRequest ddr){
        Prefeitura prefeitura = prefeituraService.getId(ddr.getIdPrefeitura());
        Demanda demanda = new Demanda();
        demanda.setStatusDemanda(StatusDemanda.ABERTA);
        demanda.setAtivo(true);
        demanda.setPrefeitura(prefeitura);
        demanda.setPrazoMaximo(ddr.getPrazoMaximo());
        demanda.setValorTotalPrefeitura(ddr.getValorTotalPrefeitura());
        String retorno = demandaService.novaDemanda(demanda);
        if (retorno.startsWith("Erro")) {
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);
    }



}
