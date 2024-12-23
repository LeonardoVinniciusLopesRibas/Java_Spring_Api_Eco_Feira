package br.com.api_eco_feira.dto.produtoprefeitura;

import br.com.api_eco_feira.enumerador.UnidadeMedida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseList {

    private Long id;
    private String nome;
    private double valorCompra;
    private UnidadeMedida unidadeMedida;

}
