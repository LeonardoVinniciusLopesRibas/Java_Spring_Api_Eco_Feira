package br.com.estudo.calculos.api_eco_feira.model.produtor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Promocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromocao;

    @Column(nullable = false)
    @NotNull(message = "O desconto é obrigatório")
    private double descontoPorcentagem;

    @Column(nullable = false)
    @NotNull(message = "A data de início da vigência é obrigatória")
    private LocalDate dataInicio = LocalDate.now();

    @Column(nullable = false)
    @NotNull(message = "A data de fim da vigência é obrigatória")
    private LocalDate dataFim;

    @OneToMany(mappedBy = "promocao")
    private List<Produto_Promocao_Associados> produto_promocao_associados = new ArrayList<>();

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuarioEmpresaId", nullable = false)
    private UsuarioEmpresa usuarioEmpresaId;
}
