package br.com.api_eco_feira.model;

import br.com.api_eco_feira.model.prefeitura.Prefeitura;
import br.com.api_eco_feira.model.produtor.Empresa;
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
public class Endereco {

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
    private Cidade cidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresaId")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prefeituraId")
    private Prefeitura prefeitura;


    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

}
