package br.com.estudo.calculos.api_eco_feira.model.prefeitura;

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
public class PerfilAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfilAcesso;

    @Column(nullable = false)
    @NotNull(message = "A descrição do perfil de acesso não foi preenchida")
    private String descricao;

    @OneToMany(mappedBy = "perfilAcesso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioSuporte> usuarioSuportes;

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

}
