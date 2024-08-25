package br.com.estudo.calculos.api_eco_feira.controller.prefeitura;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtoprefeitura")
@CrossOrigin("*")
@Tag(name = "Produto", description = "URI expostos para os produtos da prefeitura")
public class ProdutoPrefeituraController {
}
