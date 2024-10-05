package br.com.api_eco_feira.controller.central;

import br.com.api_eco_feira.dto.demandaprodutosassociados.DemandaProdutosAssociadosDtoRequest;
import br.com.api_eco_feira.model.central.Demanda_Produto_Associados;
import br.com.api_eco_feira.service.central.DemandaService;
import br.com.api_eco_feira.service.central.Demanda_Produto_Associados_Service;
import br.com.api_eco_feira.service.prefeitura.PrefeituraService;
import br.com.api_eco_feira.service.prefeitura.ProdutoPrefeituraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demandaprodutoassociados")
@CrossOrigin("*")
@Tag(name = "Relacionamento de Demanda com Produto", description = "URI expostos para a associação de produto com demanda")
public class Demanda_Produto_Associados_Controller {

    @Autowired
    private Demanda_Produto_Associados_Service demanda_produto_associados_service;

    @Autowired
    private PrefeituraService prefeituraService;

    @Autowired
    private ProdutoPrefeituraService produtoPrefeituraService;

    @Autowired
    private DemandaService demandaService;

    /*@PostMapping("/novoProdutoPrefeitura")
    @Operation(summary = "Inserção de produto da prefeitura",
            description = "Essa uri permitirá que seja inserido o produto da prefeitura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto inserido com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao inserir o produto",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> novoProdutoPrefeituraDemanda(@RequestBody @Valid DemandaProdutosAssociadosDtoRequest dpadr){
        Demanda_Produto_Associados dpa = new Demanda_Produto_Associados();
        dpa.setPrefeitura(prefeituraService.getId(dpadr.getPrefeitura()));
        dpa.setProdutoPrefeitura(produtoPrefeituraService.getId(dpadr.getProduto()));
        dpa.setDemandas(demandaService.getId(dpadr.getDemanda()));
        dpa.setQuantidade(dpadr.getQuantidade());
    }*/

}
