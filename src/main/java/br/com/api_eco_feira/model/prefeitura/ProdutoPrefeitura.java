package br.com.api_eco_feira.model.prefeitura;

import br.com.api_eco_feira.enumerador.StatusDemanda;
import br.com.api_eco_feira.enumerador.UnidadeMedida;
import br.com.api_eco_feira.model.central.Demanda_Produto_Associados;
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
public class ProdutoPrefeitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    @NotNull(message = "O nome do produto é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "O valor para compra é obrigatório")
    @Column(nullable = false)
    private double valorCompra;

    @ManyToOne
    @JoinColumn(name = "prefeituraId", nullable = false)
    private Prefeitura prefeitura;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadeMedida unidadeMedida;

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();
}
