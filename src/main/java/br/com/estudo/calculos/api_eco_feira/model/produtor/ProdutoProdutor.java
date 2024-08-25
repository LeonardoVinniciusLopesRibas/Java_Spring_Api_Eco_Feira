package br.com.estudo.calculos.api_eco_feira.model.produtor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Column(nullable = false)
    private double valorCusto;

    @NotNull(message = "O valor de venda do produto é obrigatório")
    @Column(nullable = false)
    private double valorVenda;

    @ManyToOne
    @JoinColumn(name = "grupoProdutosId", nullable = false)
    private GrupoProdutos grupoProdutos;


    @OneToMany(mappedBy = "produtoProdutor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto_Promocao_Associados> produtoPromocaoAssociados = new ArrayList<>();

    private boolean apareceEmDemandas;

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();



}
