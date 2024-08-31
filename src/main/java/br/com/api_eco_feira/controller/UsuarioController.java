package br.com.api_eco_feira.controller;

import br.com.api_eco_feira.auth.Usuario;
import br.com.api_eco_feira.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario/controlador")
@CrossOrigin("*")
@Tag(name = "Estado", description = "URI expostos para os estados do endereço")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/get/{id}")
    @Operation(summary = "Busca de usuários",
            description = "Realiza a busca de usuário por id informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários recuperados com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o usuário com id informado",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Usuario> getId(@PathVariable Long id) {
        Usuario usuario = usuarioService.getId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
