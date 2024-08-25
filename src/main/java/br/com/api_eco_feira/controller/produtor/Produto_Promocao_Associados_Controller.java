package br.com.api_eco_feira.controller.produtor;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtopromocaoassociados")
@CrossOrigin("*")
@Tag(name = "Relacionamento de Produto com Promoção", description = "URI expostos para o relacionamento de produto com promoção")
public class Produto_Promocao_Associados_Controller {
}
