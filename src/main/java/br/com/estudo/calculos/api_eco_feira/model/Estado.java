package br.com.estudo.calculos.api_eco_feira.model;

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
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;

    @Column(nullable = false)
    @NotNull(message = "O nome do estado é obrigatório")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "A sigla do estado é obrigatória")
    private String sigla;

    //MUITOS ESTADOS PARA UM PAÍS
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paisId", nullable = false)
    private Pais paisId;

    //UM ESTADO PARA MUITAS CIDADES
    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cidade> cidade = new ArrayList<>();

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuarioSuporteId", nullable = false, updatable = false)
    private UsuarioSuporte usuarioSuporteId;
}
