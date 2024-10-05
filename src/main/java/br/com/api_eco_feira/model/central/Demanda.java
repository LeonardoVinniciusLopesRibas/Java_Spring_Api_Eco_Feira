package br.com.api_eco_feira.model.central;

import br.com.api_eco_feira.enumerador.StatusDemanda;
import br.com.api_eco_feira.model.prefeitura.Prefeitura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private boolean ativo;

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private double valorTotalPrefeitura;

    //@Column(nullable = false)
    //private double valorTotalProdutor;

    @Column(nullable = false)
    private LocalDate prazoMaximo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDemanda statusDemanda;


}
