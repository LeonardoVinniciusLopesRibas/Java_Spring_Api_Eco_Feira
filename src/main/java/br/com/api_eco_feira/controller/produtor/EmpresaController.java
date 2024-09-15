package br.com.api_eco_feira.controller.produtor;

import br.com.api_eco_feira.dto.empresa.EmpresaRequest;
import br.com.api_eco_feira.model.produtor.Empresa;
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
@RequestMapping("/api/empresa")
@CrossOrigin("*")
@Tag(name = "Empresa (Produtor Rural)", description = "URI expostos para a empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("get/{id}")
    @Operation(summary = "Consulta de Empresa",
            description = "Consulta a empresa pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Empresa> getId(@PathVariable Long id) {
        Empresa empresa = empresaService.getId(id);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("put/{id}")
    @Operation(summary = "Atualização de Empresa",
            description = "Atualiza a empresa pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Empresa não atualizada",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> put(@PathVariable Long id, @RequestBody EmpresaRequest empresaRequest ){
        Empresa empresa = empresaService.getId(id);
        empresa.setNomeFantasia(empresaRequest.getNomeFantasia());
        empresa.setRazaoSocial(empresaRequest.getRazaoSocial());
        empresa.setNumeroTelefone(empresaRequest.getNumeroTelefone());
        empresa.setEmail(empresaRequest.getEmail());
        empresa.setAtividadePrincipal(empresaRequest.getAtividadePrincipal());
        empresa.setDescricao(empresaRequest.getDescricao());

        String retorno = empresaService.put(empresa);

        if(retorno.startsWith("Erro")){
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);

    }

}
