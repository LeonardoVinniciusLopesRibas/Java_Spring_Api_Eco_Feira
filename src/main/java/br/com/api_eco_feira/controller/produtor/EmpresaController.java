package br.com.api_eco_feira.controller.produtor;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin("*")
@Tag(name = "Empresa (Produtor Rural)", description = "URI expostos para a empresa")
public class EmpresaController {



}
