package br.com.api_eco_feira.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoProdutoResponseUnique {

    private Long idGrupoProduto;
    private String descricaoGrupoProduto;

}
