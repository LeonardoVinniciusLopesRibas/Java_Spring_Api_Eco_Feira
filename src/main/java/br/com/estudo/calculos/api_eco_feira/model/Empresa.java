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
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    @Column(nullable = false)
    @NotNull(message = "O nome fantasia é obrigatório")
    private String nomeFantasia;

    @Column(nullable = false)
    @NotNull(message = "O nome fantasia é obrigatório")
    private String razaoSocial;

    @Column(nullable = false)
    @NotNull(message = "O nome fantasia é obrigatório")
    private String cnpj;

    @Column(nullable = false)
    @NotNull(message = "O telefone é obrigatório")
    private String numeroTelefone;

    @Column(nullable = false)
    @NotNull(message = "O e-mail é obrigatório")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "A atividade principal é obrigatória")
    private String atividadePrincipal;

    @Column(length = 500)
    private String descricao;

    @Column(nullable = true)
    private String logoUrl;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuarioSuporteId", nullable = false, updatable = false)
    private UsuarioSuporte usuarioSuporteId;

    @ManyToOne
    @JoinColumn(name = "grupoEmpresaId", nullable = false)
    private GrupoEmpresa grupoEmpresaId;

    @OneToMany(mappedBy = "empresa")
    private List<UsuarioEmpresa_Empresa_Associados> usuarioEmpresa_empresa_associados = new ArrayList<>();
}
