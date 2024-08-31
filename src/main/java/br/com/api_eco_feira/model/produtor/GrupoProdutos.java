package br.com.api_eco_feira.model.produtor;

import br.com.api_eco_feira.auth.Usuario;
import br.com.api_eco_feira.model.Cidade;
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

    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "empresaId", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

}
