package br.com.api_eco_feira.dto.contato;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContatoResponseList {

    private Long idPrefeitura;
    private String descricaoContato;
    private String numeroTelefone;
    private String email;

}
