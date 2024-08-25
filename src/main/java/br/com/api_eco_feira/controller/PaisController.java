package br.com.api_eco_feira.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pais")
@CrossOrigin("*")
@Tag(name = "País", description = "URI expostos para os paises do endereço")
public class PaisController {
}
