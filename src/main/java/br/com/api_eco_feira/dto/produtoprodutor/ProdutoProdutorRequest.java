package br.com.api_eco_feira.dto.produtoprodutor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoProdutorRequest {

    @NotEmpty(message = "O nome do produto é obrigatório")
    private String nome;

    @NotNull(message = "O valor de custo é obrigatório")
    @Positive(message = "O valor de custo deve ser maior que zero")
    private Double valorCusto;

    @NotNull(message = "O valor de venda do produto é obrigatório")
    @Positive(message = "O valor de venda deve ser maior que zero")
    private Double valorVenda;

    @NotNull(message = "Grupo de produtos não pode estar vazio")
    private Long grupoProdutos;
    private Long idEmpresa;
    private boolean apareceEmDemandas;
    private boolean ativo;
}
