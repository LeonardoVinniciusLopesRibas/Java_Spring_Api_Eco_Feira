package br.com.estudo.calculos.api_eco_feira.model.produtor;

import br.com.estudo.calculos.api_eco_feira.model.UsuarioSuporte;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GrupoEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrupoEmpresa;

    @NotNull(message = "O nome do grupo é obrigatório")
    private String nome;

    @OneToMany(mappedBy = "grupoEmpresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Empresa> empresas;

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuarioSuporteId", nullable = false, updatable = false)
    private UsuarioSuporte usuarioSuporteId;

}
