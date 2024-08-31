package br.com.api_eco_feira.controller.produtor;

import br.com.api_eco_feira.auth.LoginResponse;
import br.com.api_eco_feira.dto.GrupoProdutosRequest;
import br.com.api_eco_feira.dto.GrupoProdutosResponse;
import br.com.api_eco_feira.model.produtor.GrupoProdutos;
import br.com.api_eco_feira.service.produtor.GrupoProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupoprodutos")
@CrossOrigin("*")
@Tag(name = "Grupo Produtos", description = "URI expostos para os grupos (departamentos) de produtos")
public class GrupoProdutosController {

    @Autowired
    private GrupoProdutosService grupoProdutosService;

    @PostMapping("/novo")
    @Operation(summary = "Cadastro de grupo",
            description = "Realiza o cadastro de novo grupo de produtos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao salvar o grupo",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> novoGrupo(@RequestBody @Valid GrupoProdutosRequest grupoProdutosRequest) {
        String resposta = grupoProdutosService.novoGrupo(grupoProdutosRequest);
        if (resposta.startsWith("Erro")) {
            return ResponseEntity.badRequest().body(resposta);
        }
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/get")
    @Operation(summary = "Busca de Grupos de Produtos",
            description = "Realiza a consulta de grupo de produtos, podendo ser filtrados por uma query.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GrupoProdutosResponse.class))),
            @ApiResponse(responseCode = "404", description = "Não foram encontrados grupos",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<GrupoProdutosResponse>> get(@RequestParam String query){
        List<GrupoProdutosResponse> grupoProdutosResponses = grupoProdutosService.get(query);

        if (grupoProdutosResponses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grupoProdutosResponses);
    }

}
