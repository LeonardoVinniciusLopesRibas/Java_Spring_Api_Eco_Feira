package br.com.api_eco_feira.dto.produtoprodutor;

import br.com.api_eco_feira.dto.grupoprodutos.GrupoProdutoResponseUnique;
import br.com.api_eco_feira.model.produtor.GrupoProdutos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoProdutorResponseUnique {

    private Long idProduto;
    private String nome;
    private double valorCusto;
    private double valorVenda;
    private GrupoProdutoResponseUnique grupoProdutosResponseUnique;
    private boolean apareceEmDemandas;

}
