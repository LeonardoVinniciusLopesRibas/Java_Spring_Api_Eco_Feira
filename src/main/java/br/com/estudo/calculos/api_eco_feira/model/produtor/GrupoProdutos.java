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
public class GrupoProdutos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrupo;

    @Column(nullable = false)
    @NotNull(message = "O nome do grupo é obrigatório")
    private String descricaoGrupo;

    @ManyToOne
    @JoinColumn(name = "usuarioEmpresaId", nullable = false)
    private UsuarioEmpresa usuarioEmpresaId;

    @OneToMany(mappedBy = "grupoProdutos")
    private List<Produto> produtos = new ArrayList<>();

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

}
