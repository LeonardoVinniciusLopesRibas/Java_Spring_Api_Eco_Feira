package br.com.estudo.calculos.api_eco_feira.controller.produtor;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grupoprodutos")
@CrossOrigin("*")
@Tag(name = "Grupo Produtos", description = "URI expostos para os grupos (departamentos) de produtos")
public class GrupoProdutosController {
}
