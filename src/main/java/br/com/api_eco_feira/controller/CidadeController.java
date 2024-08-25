package br.com.api_eco_feira.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cidade")
@CrossOrigin("*")
@Tag(name = "Cidade", description = "URI expostos para as cidades do endereço")
public class CidadeController {
}
