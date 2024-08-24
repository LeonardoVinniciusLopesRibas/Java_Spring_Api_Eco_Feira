package br.com.estudo.calculos.api_eco_feira.model.prefeitura;

import br.com.estudo.calculos.api_eco_feira.model.central.DemandaProduto;
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
public class Produto {

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
    @JoinColumn(name = "usuarioPrefeituraId", nullable = false)
    private UsuarioPrefeitura usuarioPrefeituraId;

    @ManyToOne
    @JoinColumn(name = "prefeituraId", nullable = false)
    private Prefeitura prefeitura;

    @OneToMany(mappedBy = "produto")
    private List<DemandaProduto> demandasProdutos = new ArrayList<>();

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();
}
