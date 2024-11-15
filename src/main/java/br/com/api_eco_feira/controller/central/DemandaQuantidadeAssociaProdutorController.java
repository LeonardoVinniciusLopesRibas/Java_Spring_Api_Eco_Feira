package br.com.api_eco_feira.controller.central;

import br.com.api_eco_feira.dto.quantidade.QuantidadeAtendidaResponseList;
import br.com.api_eco_feira.model.central.Demanda;
import br.com.api_eco_feira.model.central.DemandaQuantidadeAssociaProdutor;
import br.com.api_eco_feira.model.central.Demanda_Produto_Associados;
import br.com.api_eco_feira.model.produtor.Empresa;
import br.com.api_eco_feira.service.central.DemandaQuantidadeAssociaProdutorService;
import br.com.api_eco_feira.service.central.DemandaService;
import br.com.api_eco_feira.service.central.Demanda_Produto_Associados_Service;
import br.com.api_eco_feira.service.produtor.EmpresaService;
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
@RequestMapping("/api/demanda/quantidade/associa/produtor")
@CrossOrigin("*")
@Tag(name = "Demanda", description = "URI expostos para o produtor atender a quantidade que ele deseja")
public class DemandaQuantidadeAssociaProdutorController {

    @Autowired
    private DemandaQuantidadeAssociaProdutorService demandaQuantidadeAssociaProdutorService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private DemandaService demandaService;

    @Autowired
    private Demanda_Produto_Associados_Service demanda_produto_associados_service;

    @PostMapping("/atendendo/{idEmpresa}/{idDemanda}/{idDemandaProdutosAssociados}/{quantidade}")
    @Operation(summary = "uri para atender produto da demanda",
            description = "essa uri serve para dar post em produtos da demanda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nova quantidade atendida",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao atender a demanda",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> atender(@PathVariable Long idEmpresa, @PathVariable Long idDemanda,
                                          @PathVariable Long idDemandaProdutosAssociados,
                                          @PathVariable double quantidade) {
        Demanda_Produto_Associados demandaProdutoAssociados =
                demanda_produto_associados_service.findById(idDemandaProdutosAssociados);
        if (quantidade > (demandaProdutoAssociados.getQuantidade() - demandaProdutoAssociados.getSaldo())) {
            return ResponseEntity.badRequest().body("Quantidade não pode ser maior que o saldo restante");
        } else if (quantidade <= 0) {
            return ResponseEntity.badRequest().body("Quantidade não pode ser negativa ou zero");
        } else {
            demandaProdutoAssociados.setSaldo(demandaProdutoAssociados.getSaldo() + quantidade);
            demanda_produto_associados_service.atualizar(demandaProdutoAssociados);

            Empresa empresa = empresaService.getId(idEmpresa);
            Demanda demanda = demandaService.getId(idDemanda);


            DemandaQuantidadeAssociaProdutor demandaQuantidadeAssociaProdutor = new DemandaQuantidadeAssociaProdutor();
            demandaQuantidadeAssociaProdutor.setQuantidade(quantidade);
            demandaQuantidadeAssociaProdutor.setDemanda(demanda);
            demandaQuantidadeAssociaProdutor.setEmpresa(empresa);
            demandaQuantidadeAssociaProdutor.setDemandaProdutoAssociados(demandaProdutoAssociados);

            String retorno = demandaQuantidadeAssociaProdutorService.atendendo(demandaQuantidadeAssociaProdutor);

            if (retorno.startsWith("Erro")) {
                return ResponseEntity.badRequest().body(retorno);
            }
            return ResponseEntity.ok(retorno);
        }

    }

    @GetMapping("/getatendimento/{idDemanda}/{idProdutosAssociados}")
    @Operation(summary = "uri para pegar os atendimentos da demanda",
            description = "essa uri serve para dar get no atendimento da demanda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atendimentos recuperados com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao recuperar as demandas",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<QuantidadeAtendidaResponseList>> getAtendimento(@PathVariable Long idDemanda, @PathVariable Long idProdutosAssociados){
        List<QuantidadeAtendidaResponseList> quantidadeAtendidaResponseList = demandaQuantidadeAssociaProdutorService.getAtendimento(idDemanda, idProdutosAssociados);
        if(quantidadeAtendidaResponseList == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quantidadeAtendidaResponseList);
    }

    @DeleteMapping("deleteQuantidadeAtendida/idquantidadeatendida/{idQuantidadeAtendida}/iddemanda/{idDemanda}/quantidade/{quantidade}")
    @Operation(summary = "uri para deletar um atendimento da demanda",
            description = "essa uri serve para dar delete no atendimento da demanda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atendimento deletado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao deletar as demandas",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> deleteQuantidadeAtendida(@PathVariable Long idQuantidadeAtendida, @PathVariable Long idDemanda, @PathVariable double quantidade){
        String retorno = demandaQuantidadeAssociaProdutorService.delete(idQuantidadeAtendida, idDemanda, quantidade);
        if(retorno.startsWith("Erro")){
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);
    }

}
