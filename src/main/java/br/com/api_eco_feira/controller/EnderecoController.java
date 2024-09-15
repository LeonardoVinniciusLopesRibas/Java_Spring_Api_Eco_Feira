package br.com.api_eco_feira.controller;

import br.com.api_eco_feira.dto.endereco.EnderecoRequest;
import br.com.api_eco_feira.model.Endereco;
import br.com.api_eco_feira.service.CidadeService;
import br.com.api_eco_feira.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/endereco")
@CrossOrigin("*")
@Tag(name = "Endereço", description = "URI expostos para os endereços")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private CidadeService cidadeService;

    @PutMapping("/put/{id}")
    @Operation(summary = "Atualizar o endereço pelo id",
            description = "Realiza a atualização de uma empresa pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço da empresa atualizada pelo ID",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar o endereço pelo id",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> put(@PathVariable Long id, @RequestBody EnderecoRequest enderecoRequest){
        Endereco endereco = enderecoService.getId(id);

        endereco.setCep(enderecoRequest.getCep());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setNumero(enderecoRequest.getNumero());
        endereco.setComplemento(enderecoRequest.getComplemento());
        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setCidade(cidadeService.getId(enderecoRequest.getCidadeId()));
        String retorno = enderecoService.put(endereco);

        if(retorno.startsWith("Erro")){
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);

    }

}
