package br.com.api_eco_feira.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoProdutosRequest {

    private String descricaoGrupo;
    private Long empresaId;
    private Long usuarioId;

}
