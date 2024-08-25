package br.com.estudo.calculos.api_eco_feira.model.central;

import br.com.estudo.calculos.api_eco_feira.model.prefeitura.ProdutoPrefeitura;
import br.com.estudo.calculos.api_eco_feira.model.produtor.Empresa;
import br.com.estudo.calculos.api_eco_feira.model.produtor.ProdutoProdutor;
import jakarta.persistence.*;
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
    @JoinColumn(name = "produtorId", nullable = true)
    private Empresa produtor;

    private int quantidade;

    private boolean atendido = false;

    private LocalDateTime dataAtendimento;
}
