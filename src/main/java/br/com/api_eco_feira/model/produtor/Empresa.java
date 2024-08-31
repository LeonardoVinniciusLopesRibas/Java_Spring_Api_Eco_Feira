package br.com.api_eco_feira.model.produtor;

import br.com.api_eco_feira.auth.Usuario;
import br.com.api_eco_feira.model.Endereco;
import br.com.api_eco_feira.model.prefeitura.Prefeitura;
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
    @NotNull(message = "A razão social é obrigatória")
    private String razaoSocial;

    @Column(nullable = false)
    @NotNull(message = "O cnpj é obrigatório")
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
    @JoinColumn(name = "grupoEmpresa", nullable = false)
    private GrupoEmpresa grupoEmpresaId;

    @OneToMany(mappedBy = "empresaAssociation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> usuario;


}