package br.com.api_eco_feira.model.central;

import br.com.api_eco_feira.model.prefeitura.Prefeitura;
import br.com.api_eco_feira.model.prefeitura.ProdutoPrefeitura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Demanda_Produto_Associados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemandaProduto;

    @ManyToOne
    @JoinColumn(name = "demandaId", nullable = false)
    private Demanda demandas;

    @ManyToOne
    @JoinColumn(name = "produtoId", nullable = false)
    private ProdutoPrefeitura produtoPrefeitura;

    @ManyToOne
    @JoinColumn(name = "prefeituraId", nullable = true)
    private Prefeitura prefeitura;

    @Column(nullable = false)
    private double valorPrefeitura;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private double saldo;

}
