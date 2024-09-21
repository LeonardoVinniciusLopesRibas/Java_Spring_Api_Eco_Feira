package br.com.api_eco_feira.dto.produtoprefeitura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPrefeituraRequest {

    private String nome;
    private double valorCompra;


}
