package br.com.estudo.calculos.api_eco_feira.model.prefeitura;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrefeitura;

    @ManyToOne
    @JoinColumn(name = "prefeituraId", nullable = false)
    private Prefeitura prefeitura;

    @Column(nullable = false)
    @NotNull(message = "Descrição do contato é obrigatória")
    private String descricaoContato;

    @Column(nullable = false)
    @NotNull(message = "O telefone é obrigatório")
    private String numeroTelefone;

    private String email;

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

}
