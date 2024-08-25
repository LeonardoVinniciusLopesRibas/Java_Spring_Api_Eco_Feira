package br.com.estudo.calculos.api_eco_feira.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estado")
@CrossOrigin("*")
@Tag(name = "Estado", description = "URI expostos para os estados do endereço")
public class EstadoController {
}
