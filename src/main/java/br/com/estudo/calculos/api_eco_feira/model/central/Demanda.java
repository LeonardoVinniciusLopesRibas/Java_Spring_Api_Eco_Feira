package br.com.estudo.calculos.api_eco_feira.model.central;

import br.com.estudo.calculos.api_eco_feira.enumerador.StatusDemanda;
import br.com.estudo.calculos.api_eco_feira.model.prefeitura.Prefeitura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Demanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemanda;

    @ManyToOne
    @JoinColumn(name = "prefeituraId", nullable = false)
    private Prefeitura prefeitura;

    @OneToMany(mappedBy = "demanda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DemandaProduto> demandasProdutos = new ArrayList<>();

    private boolean ativo;

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private double valorTotalPrefeitura;

    @Column(nullable = false)
    private double valorTotalProdutor;

    @Column(nullable = false)
    private LocalDate prazoMaximo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDemanda statusDemanda;


}
