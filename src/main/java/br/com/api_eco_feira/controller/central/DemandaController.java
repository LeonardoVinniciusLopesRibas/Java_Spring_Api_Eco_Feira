package br.com.api_eco_feira.controller.central;

import br.com.api_eco_feira.dto.demanda.DemandaDtoRequest;
import br.com.api_eco_feira.dto.demanda.DemandaDtoResponse;
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
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/demanda")
@CrossOrigin("*")
@Tag(name = "Demanda", description = "URI expostos para demanda")
public class DemandaController {

    @Autowired
    private DemandaService demandaService;

    @Autowired
    private PrefeituraService prefeituraService;

    @PostMapping("/new")
    @Operation(summary = "uri para nova demanda",
            description = "essa uri serve para dar post em uma demanda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nova demanda",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao gerar uma demanda",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Long> novaDemanda (@RequestBody DemandaDtoRequest ddr){
        Prefeitura prefeitura = prefeituraService.getId(ddr.getIdPrefeitura());
        Demanda demanda = new Demanda();
        demanda.setStatusDemanda(StatusDemanda.ABERTA);
        demanda.setAtivo(true);
        demanda.setPrefeitura(prefeitura);
        demanda.setDescricao(ddr.getDescricao());
        demanda.setPrazoMaximo(ddr.getPrazoMaximo());
        demanda.setValorTotalPrefeitura(ddr.getValorTotalPrefeitura());
        Long idRetornado = demandaService.novaDemanda(demanda);

        return ResponseEntity.ok(idRetornado);
    }

    @GetMapping("/get")
    @Operation(summary = "uri para pegar as demandas",
            description = "essa uri serve para pegar as demandas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Demandas recuperadas com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao recuperas as demanda",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<DemandaDtoResponse>> get(@RequestParam Long idPrefeitura){
        Prefeitura prefeitura = prefeituraService.getId(idPrefeitura);
        List<DemandaDtoResponse> ddr = demandaService.get(prefeitura);

        return ResponseEntity.ok(ddr);
    }

    @GetMapping("/getAberta")
    @Operation(summary = "uri para pegar as demandas",
            description = "essa uri serve para pegar as demandas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Demandas recuperadas com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao recuperas as demanda",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<DemandaDtoResponse>> getAberta(@RequestParam Long idPrefeitura){
        Prefeitura prefeitura = prefeituraService.getId(idPrefeitura);
        List<DemandaDtoResponse> ddr = demandaService.getAberta(prefeitura);

        return ResponseEntity.ok(ddr);
    }

    /*@GetMapping("/get/{id}")
    @Operation(summary = "uri para pegar as demandas pelo id",
    description = "Essa uri é para pegar as demandas pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Demanda recuperada com sucesso",
            content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não encontrado o produto",
            content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<DemandaDtoResponse> getById(@PathVariable Long id){
        DemandaDtoResponse demandaDtoResponse = demandaService.getId()
    }
*/


    @GetMapping("/getDemandasByIbge/{ibge}")
    @Operation(summary = "uri para pegar as demandas",
            description = "essa uri serve para pegar as demandas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Demandas recuperadas com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao recuperas as demanda",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<DemandaDtoResponse>> getDemandasByIbge(@PathVariable int ibge){
        List<DemandaDtoResponse> ddr = demandaService.getDemandasByIbge(ibge);

        return ResponseEntity.ok(ddr);
    }

}
