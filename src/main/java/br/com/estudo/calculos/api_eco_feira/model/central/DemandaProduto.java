package br.com.estudo.calculos.api_eco_feira.model.central;

import br.com.estudo.calculos.api_eco_feira.model.produtor.Empresa;
import br.com.estudo.calculos.api_eco_feira.model.produtor.Produto;
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
public class DemandaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemandaProduto;

    @ManyToOne
    @JoinColumn(name = "demandaId", nullable = false)
    private Demanda demandas;

    @ManyToOne
    @JoinColumn(name = "produtoId", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "produtorId", nullable = true)
    private Empresa produtor;

    private int quantidade;

    private boolean atendido = false;

    private LocalDateTime dataAtendimento;
}
