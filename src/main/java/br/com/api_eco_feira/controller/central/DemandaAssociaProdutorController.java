package br.com.api_eco_feira.controller.central;

import br.com.api_eco_feira.model.central.Demanda;
import br.com.api_eco_feira.model.central.DemandaAssociaProdutor;
import br.com.api_eco_feira.model.produtor.Empresa;
import br.com.api_eco_feira.service.central.DemandaAssociaProdutorService;
import br.com.api_eco_feira.service.central.DemandaService;
import br.com.api_eco_feira.service.produtor.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demanda/associa/produtor")
@CrossOrigin("*")
@Tag(name = "Demanda Associa Produtor", description = "Esse endpoint associa a demanda de uma prefeitura à um produtor")
public class DemandaAssociaProdutorController {

    @Autowired
    private DemandaAssociaProdutorService demandaAssociaProdutorService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private DemandaService demandaService;

    @PostMapping("/{idProdutor}/{idDemanda}")
    @Operation(summary = "Vincula uma demanda à um produtor",
            description = "Vincula uma demanda à um produtor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao cadastrar usuário",
                    content = @Content(mediaType = "application/json"))
    })
    private ResponseEntity<String> vincularDemandaAoProdutor(@PathVariable Long idProdutor, @PathVariable Long idDemanda) {
        Empresa empresa = empresaService.getId(idProdutor);
        System.out.println(empresa);
        Demanda demanda = demandaService.getId(idDemanda);
        DemandaAssociaProdutor dap = new DemandaAssociaProdutor();
        dap.setDemanda(demanda);
        dap.setEmpresa(empresa);
        String retorno = demandaAssociaProdutorService.vincularDemandaAoProdutor(dap);
        if(retorno.startsWith("Erro")){
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);
    }




}
