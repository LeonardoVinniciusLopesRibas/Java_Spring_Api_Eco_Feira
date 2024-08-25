package br.com.api_eco_feira.controller.prefeitura;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prefeitura")
@CrossOrigin("*")
@Tag(name = "Prefeitura", description = "URI expostos para a prefeitura")
public class PrefeituraController {
}
