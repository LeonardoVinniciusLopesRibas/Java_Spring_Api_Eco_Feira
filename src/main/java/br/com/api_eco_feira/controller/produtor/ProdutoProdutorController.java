package br.com.api_eco_feira.controller.produtor;

import br.com.api_eco_feira.model.produtor.ProdutoProdutor;
import br.com.api_eco_feira.service.produtor.ProdutoProdutorService;
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
@RequestMapping("/api/produtoprodutor")
@CrossOrigin("*")
@Tag(name = "Produto do Produtor", description = "URI expostos para os Produtos do Produtor")
public class ProdutoProdutorController {

    @Autowired
    private ProdutoProdutorService produtoProdutorService;


    @PostMapping("/post")
    @Operation(summary = "Cadastro de produto",
            description = "Essa URI permitirá o usuário a cadastrar seus produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao cadastrar produto",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> post(@RequestBody ProdutoProdutor produtoProdutor){
        String retorno = produtoProdutorService.post(produtoProdutor);

        if(retorno.startsWith("Erro")){
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);
    }

}
