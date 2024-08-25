package br.com.estudo.calculos.api_eco_feira.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/endereco")
@CrossOrigin("*")
@Tag(name = "Endereço", description = "URI expostos para os endereços")
public class EnderecoController {
}
