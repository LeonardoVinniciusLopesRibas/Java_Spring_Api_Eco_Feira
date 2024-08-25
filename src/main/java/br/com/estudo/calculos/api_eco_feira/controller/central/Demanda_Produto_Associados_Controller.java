package br.com.estudo.calculos.api_eco_feira.controller.central;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demandaprodutoassociados")
@CrossOrigin("*")
@Tag(name = "Relacionamento de Demanda com Produto", description = "URI expostos para a associação de produto com demanda")
public class Demanda_Produto_Associados_Controller {
}
