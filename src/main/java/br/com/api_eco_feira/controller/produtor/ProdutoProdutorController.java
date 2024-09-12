package br.com.api_eco_feira.controller.produtor;

import br.com.api_eco_feira.dto.produtoprodutor.ProdutoProdutorResponse;
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

    @GetMapping("/get")
    @Operation(summary = "Busca os produtos",
            description = "Essa URI permitirá o usuário buscar seus produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos buscados com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao buscar produto",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não foram encontrados produtos para serem listados",
            content =  @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<ProdutoProdutorResponse>> get(@RequestParam String query, @RequestParam String cnpj){
        List<ProdutoProdutorResponse> produtoProdutorResponses = produtoProdutorService.get(query, cnpj);
        if(produtoProdutorResponses.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoProdutorResponses);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Busca um produto pelo ID",
            description = "Essa URI permitirá o usuário buscar um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto buscado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao buscar produto",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado produto",
                    content =  @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ProdutoProdutor> get(@PathVariable Long id){
        ProdutoProdutor produtoProdutor = produtoProdutorService.getId(id);

        if(produtoProdutor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoProdutor);
    }

    @PutMapping("/put/{id}")
    @Operation(summary = "Atualizar um produto pelo ID",
            description = "Essa URI permitirá o usuário atualizar um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao atualizar produto",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> putId(@PathVariable Long id, @RequestBody ProdutoProdutor produtoProdutor){
        String retorno = produtoProdutorService.put(produtoProdutor, id);
        if (retorno.startsWith("Erro")) {
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);
    }

    @PutMapping("/put/ativo/{id}")
    @Operation(summary = "Atualizar um false/true pelo ID",
            description = "Essa URI permitirá o usuário atualizar um produto se é true ou false pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao atualizar produto",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> putId(@RequestParam boolean ativo, @PathVariable Long id){
        String retorno = produtoProdutorService.putAtivo(ativo, id);
        if (retorno.startsWith("Erro")) {
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);
    }


}
