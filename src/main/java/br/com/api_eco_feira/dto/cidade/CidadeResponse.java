package br.com.api_eco_feira.dto.cidade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeResponse {

    private Long idCidade;
    private String nome;

}
