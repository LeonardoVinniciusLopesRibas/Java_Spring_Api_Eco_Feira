package br.com.estudo.calculos.api_eco_feira.model.prefeitura;

import br.com.estudo.calculos.api_eco_feira.model.Cidade;
import br.com.estudo.calculos.api_eco_feira.model.produtor.UsuarioEmpresa;
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
public class EnderecoPrefeitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;

    @Column(nullable = false)
    @NotNull(message = "A rua é obrigatória")
    private String logradouro;

    @Column(nullable = false)
    @NotNull(message = "O número é obrigatório")
    private String numero;

    private String complemento;

    @Column(nullable = false)
    @NotNull(message = "O bairro é obrigatório")
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidadeId", nullable = false)
    private Cidade cidadeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prefeituraId", nullable = false)
    private Prefeitura prefeituraId;

    @ManyToOne
    @JoinColumn(name = "usuarioEmpresaId", nullable = false)
    private UsuarioEmpresa usuarioEmpresaId;

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();


}
