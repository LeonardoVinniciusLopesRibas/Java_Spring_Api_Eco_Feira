package br.com.api_eco_feira.controller.prefeitura;

import br.com.api_eco_feira.dto.prefeitura.PrefeituraRequest;
import br.com.api_eco_feira.model.prefeitura.Prefeitura;
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
@RequestMapping("/api/prefeitura")
@CrossOrigin("*")
@Tag(name = "Prefeitura", description = "URI expostos para a prefeitura")
public class PrefeituraController {

    @Autowired
    private PrefeituraService prefeituraService;

    @GetMapping("get/{id}")
    @Operation(summary = "Consulta de Empresa",
            description = "Consulta a empresa pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Prefeitura> getId(@PathVariable Long id){
        Prefeitura prefeitura = prefeituraService.getId(id);
        if(prefeitura !=null){
            return ResponseEntity.ok(prefeitura);
        }
        return ResponseEntity.notFound().build();
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
    public ResponseEntity<String> put (@PathVariable Long id, @RequestBody PrefeituraRequest prefeituraRequest){
        Prefeitura prefeitura = prefeituraService.getId(id);
        prefeitura.setCnpj(prefeituraRequest.getCnpj());
        prefeitura.setRazaoSocial(prefeituraRequest.getRazaoSocial());
        prefeitura.setNomeFantasia(prefeituraRequest.getNomeFantasia());

        String retorno = prefeituraService.put(prefeitura);

        if(retorno.startsWith("Erro")){
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);
    }


}
