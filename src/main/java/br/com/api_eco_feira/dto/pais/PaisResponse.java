package br.com.api_eco_feira.dto.pais;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaisResponse {

    private Long idPais;
    private String nome;
    private String sigla;

}
