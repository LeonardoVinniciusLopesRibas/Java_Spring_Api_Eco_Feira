package br.com.api_eco_feira.model.produtor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProdutoProdutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    @NotNull(message = "O nome do produto é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "O valor de custo é obrigatório")
    @Positive(message = "O valor de custo deve ser maior que zero")
    @Column(nullable = false)
    private Double valorCusto;

    @NotNull(message = "O valor de venda do produto é obrigatório")
    @Positive(message = "O valor de venda deve ser maior que zero")
    @Column(nullable = false)
    private Double valorVenda;

    @ManyToOne
    @JoinColumn(name = "grupoProdutosId", nullable = false)
    private GrupoProdutos grupoProdutos;

    @ManyToOne
    @JoinColumn(name = "empresaId", nullable = false)
    private Empresa empresa;

    private boolean apareceEmDemandas;

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();
}
