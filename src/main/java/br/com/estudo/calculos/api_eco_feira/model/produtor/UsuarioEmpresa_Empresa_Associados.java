package br.com.estudo.calculos.api_eco_feira.model.produtor;

import jakarta.persistence.*;
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
public class UsuarioEmpresa_Empresa_Associados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuarioEmpresa_EmpresaAssociation;

    @ManyToOne
    @JoinColumn(name = "usuarioEmpresaId", nullable = false)
    private UsuarioEmpresa usuarioEmpresaId;

    @ManyToOne
    @JoinColumn(name = "empresaId", nullable = false)
    private Empresa empresaId;

    @Column(nullable = false)
    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

}
