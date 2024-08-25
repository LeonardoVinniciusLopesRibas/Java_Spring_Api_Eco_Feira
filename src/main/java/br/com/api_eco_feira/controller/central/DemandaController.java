package br.com.api_eco_feira.controller.central;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demanda")
@CrossOrigin("*")
@Tag(name = "Demanda", description = "URI expostos para demanda")
public class DemandaController {
}
