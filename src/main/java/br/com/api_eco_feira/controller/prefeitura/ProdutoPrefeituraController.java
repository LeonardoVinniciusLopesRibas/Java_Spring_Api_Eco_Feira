package br.com.api_eco_feira.controller.prefeitura;

import br.com.api_eco_feira.dto.produtoprefeitura.ProdutoPrefeituraRequest;
import br.com.api_eco_feira.dto.produtoprefeitura.ProdutoResponseList;
import br.com.api_eco_feira.dto.produtoprefeitura.ProdutoResponseUnique;
import br.com.api_eco_feira.model.prefeitura.ProdutoPrefeitura;
import br.com.api_eco_feira.model.produtor.ProdutoProdutor;
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

import java.util.List;

@RestController
@RequestMapping("/api/produtoprefeitura")
@CrossOrigin("*")
@Tag(name = "Produto", description = "URI expostos para os produtos da prefeitura")
public class ProdutoPrefeituraController {

    @Autowired
    private ProdutoPrefeituraService produtoPrefeituraService;

    @Autowired
    private PrefeituraService prefeituraService;

    @PostMapping("/post/{idPrefeitura}")
    @Operation(summary = "Cadastro de produto",
            description = "Essa URI permitirá o usuário a cadastrar seus produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao cadastrar produto",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> post(@RequestBody ProdutoPrefeituraRequest ppr, @PathVariable Long idPrefeitura){

        ProdutoPrefeitura produtoPrefeitura = new ProdutoPrefeitura();
        produtoPrefeitura.setNome(ppr.getNome());
        produtoPrefeitura.setValorCompra(ppr.getValorCompra());
        produtoPrefeitura.setAtivo(true);
        produtoPrefeitura.setPrefeitura(prefeituraService.getId(idPrefeitura));

        String retorno = produtoPrefeituraService.post(produtoPrefeitura);

        if(retorno.startsWith("Erro")){
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("/get")
    @Operation(summary = "Buscar os produtos da prefeitura",
            description = "Essa URI permitirá o usuário a buscar seus produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto buscado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao buscar produto",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Lista está vazia",
                    content = @Content(mediaType = "application/json"))
    })
    private ResponseEntity<List<ProdutoResponseList>> get(@RequestParam String query, @RequestParam String cnpj){
        List<ProdutoResponseList> produtoResponseLists = produtoPrefeituraService.get(query, cnpj);
        if(produtoResponseLists.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoResponseLists);
    }

    @GetMapping("/getDesativados")
    @Operation(summary = "Busca produtos desativados",
    description = "Essa uri vai buscar os produtos desativados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos desativados recuperados com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao buscar produtos desativados",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não foram encontrados produtos desativados para serem listados",
                    content =  @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<ProdutoResponseList>> getDesativados(@RequestParam String query, @RequestParam String cnpj){
        List<ProdutoResponseList> produtoResponseLists = produtoPrefeituraService.getDesativados(query, cnpj);
        if(produtoResponseLists.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoResponseLists);
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
    public ResponseEntity<ProdutoResponseUnique> getId(@PathVariable Long id){
        ProdutoPrefeitura produtoPrefeitura = produtoPrefeituraService.getId(id);
        ProdutoResponseUnique produtoResponseUnique = new ProdutoResponseUnique();
        produtoResponseUnique.setId(produtoPrefeitura.getIdProduto());
        produtoResponseUnique.setNome(produtoPrefeitura.getNome());
        produtoResponseUnique.setValorCompra(produtoPrefeitura.getValorCompra());

        if(produtoPrefeitura == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoResponseUnique);
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
    public ResponseEntity<String> putId(@PathVariable Long id, @RequestBody @Valid ProdutoPrefeituraRequest produtoPrefeituraRequest){
        ProdutoPrefeitura produtoRetornado = produtoPrefeituraService.getId(id);
        produtoRetornado.setIdProduto(id);
        produtoRetornado.setNome(produtoPrefeituraRequest.getNome());
        produtoRetornado.setValorCompra(produtoPrefeituraRequest.getValorCompra());
        String retorno = produtoPrefeituraService.put(produtoRetornado);
        if(retorno.startsWith("Erro")){
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
        String retorno = produtoPrefeituraService.putAtivo(ativo, id);
        if (retorno.startsWith("Erro")) {
            return ResponseEntity.badRequest().body(retorno);
        }
        return ResponseEntity.ok(retorno);
    }

}
