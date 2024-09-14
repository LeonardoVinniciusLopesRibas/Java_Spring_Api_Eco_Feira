package br.com.api_eco_feira.controller.produtor;

import br.com.api_eco_feira.dto.grupoprodutos.GrupoProdutoResponseUnique;
import br.com.api_eco_feira.dto.produtoprodutor.ProdutoProdutorRequest;
import br.com.api_eco_feira.dto.produtoprodutor.ProdutoProdutorResponse;
import br.com.api_eco_feira.dto.produtoprodutor.ProdutoProdutorResponseUnique;
import br.com.api_eco_feira.model.produtor.ProdutoProdutor;
import br.com.api_eco_feira.service.produtor.EmpresaService;
import br.com.api_eco_feira.service.produtor.GrupoProdutosService;
import br.com.api_eco_feira.service.produtor.ProdutoProdutorService;
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
@RequestMapping("/api/produtoprodutor")
@CrossOrigin("*")
@Tag(name = "Produto do Produtor", description = "URI expostos para os Produtos do Produtor")
public class ProdutoProdutorController {

    @Autowired
    private ProdutoProdutorService produtoProdutorService;

    @Autowired
    private GrupoProdutosService grupoProdutosService;

    @Autowired
    private EmpresaService empresaService;


    @PostMapping("/post")
    @Operation(summary = "Cadastro de produto",
            description = "Essa URI permitirá o usuário a cadastrar seus produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao cadastrar produto",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> post(@RequestBody @Valid ProdutoProdutorRequest produtoProdutorRequest){
        ProdutoProdutor produtoProdutor = new ProdutoProdutor();
        produtoProdutor.setAtivo(true);
        produtoProdutor.setEmpresa(empresaService.getId(produtoProdutorRequest.getIdEmpresa()));
        produtoProdutor.setGrupoProdutos(grupoProdutosService.getById(produtoProdutorRequest.getGrupoProdutos()));
        produtoProdutor.setNome(produtoProdutorRequest.getNome());
        produtoProdutor.setValorCusto(produtoProdutorRequest.getValorCusto());
        produtoProdutor.setValorVenda(produtoProdutorRequest.getValorVenda());
        produtoProdutor.setApareceEmDemandas(produtoProdutorRequest.isApareceEmDemandas());

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

    @GetMapping("/getDesativados")
    @Operation(summary = "Busca os produtos desativados",
            description = "Essa URI permitirá o usuário buscar seus produtos desativados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos desativados buscados com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao buscar produtos desativados",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não foram encontrados produtos desativados para serem listados",
                    content =  @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<ProdutoProdutorResponse>> getDesativados(@RequestParam String query, @RequestParam String cnpj){
        List<ProdutoProdutorResponse> produtoProdutorResponses = produtoProdutorService.getDesativados(query, cnpj);
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
    public ResponseEntity<ProdutoProdutorResponseUnique> get(@PathVariable Long id){
        ProdutoProdutor produtoProdutor = produtoProdutorService.getId(id);
        ProdutoProdutorResponseUnique produtoProdutorResponseUnique = new ProdutoProdutorResponseUnique();
        produtoProdutorResponseUnique.setIdProduto(produtoProdutor.getIdProduto());
        produtoProdutorResponseUnique.setNome(produtoProdutor.getNome());
        produtoProdutorResponseUnique.setValorVenda(produtoProdutor.getValorVenda());
        produtoProdutorResponseUnique.setValorCusto(produtoProdutor.getValorCusto());
        GrupoProdutoResponseUnique gpru = new GrupoProdutoResponseUnique();
        gpru.setIdGrupoProduto(produtoProdutor.getGrupoProdutos().getIdGrupo());
        gpru.setDescricaoGrupoProduto(produtoProdutor.getGrupoProdutos().getDescricaoGrupo());
        produtoProdutorResponseUnique.setGrupoProdutosResponseUnique(gpru);
        produtoProdutorResponseUnique.setApareceEmDemandas(produtoProdutor.isApareceEmDemandas());

        if(produtoProdutor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoProdutorResponseUnique);
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
    public ResponseEntity<String> putId(@PathVariable Long id, @RequestBody @Valid ProdutoProdutorRequest produtoProdutorRequest){
        ProdutoProdutor produtoProdutor = new ProdutoProdutor();
        produtoProdutor.setGrupoProdutos(grupoProdutosService.getById(produtoProdutorRequest.getGrupoProdutos()));
        produtoProdutor.setAtivo(true);
        produtoProdutor.setNome(produtoProdutorRequest.getNome());
        produtoProdutor.setValorVenda(produtoProdutorRequest.getValorVenda());
        produtoProdutor.setValorCusto(produtoProdutorRequest.getValorCusto());
        produtoProdutor.setIdProduto(id);
        produtoProdutor.setApareceEmDemandas(produtoProdutorRequest.isApareceEmDemandas());
        produtoProdutor.setEmpresa(empresaService.getId(produtoProdutorRequest.getIdEmpresa()));


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
