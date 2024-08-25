package br.com.api_eco_feira.controller.produtor;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtoprodutor")
@CrossOrigin("*")
@Tag(name = "Produto do Produtor", description = "URI expostos para os Produtos do Produtor")
public class ProdutoProdutorController {
}
