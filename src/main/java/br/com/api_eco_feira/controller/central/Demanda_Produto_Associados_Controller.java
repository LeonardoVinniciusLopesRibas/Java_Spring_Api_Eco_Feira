package br.com.api_eco_feira.controller.central;

import br.com.api_eco_feira.dto.demandaprodutosassociados.DemandaProdutoResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/novoProdutoPrefeitura")
    @Operation(summary = "Inserção de produto da prefeitura",
            description = "Essa uri permitirá que seja inserido o produto da prefeitura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao inserir o produto",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Void> novoProdutoPrefeituraDemanda(@RequestBody @Valid DemandaProdutosAssociadosDtoRequest dpadr) {
        try {
            Demanda_Produto_Associados dpa = new Demanda_Produto_Associados();
            dpa.setPrefeitura(prefeituraService.getId(dpadr.getPrefeitura()));
            dpa.setProdutoPrefeitura(produtoPrefeituraService.getId(dpadr.getProduto()));
            dpa.setDemandas(demandaService.getId(dpadr.getDemanda()));
            dpa.setQuantidade(dpadr.getQuantidade());
            dpa.setValorPrefeitura(dpadr.getValorPrefeitura());

            demanda_produto_associados_service.novoProdutoPrefeituraDemanda(dpa);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/get/{idDemanda}")
    @Operation(summary = "Recuperar os produtos por um ID",
            description = "Essa uri permite recuperar os id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperados"),
            @ApiResponse(responseCode = "404", description = "Nada encontrado",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<DemandaProdutoResponse>> getProdutosDemanda(@PathVariable Long idDemanda){
        List<DemandaProdutoResponse> listaDemandas = demanda_produto_associados_service.getProdutos(idDemanda);
        if(listaDemandas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listaDemandas);
    }

}
