package br.com.estudo.calculos.api_eco_feira.model;

import br.com.estudo.calculos.api_eco_feira.model.prefeitura.EnderecoPrefeitura;
import br.com.estudo.calculos.api_eco_feira.model.produtor.EnderecoProdutor;
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
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCidade;

    @Column(nullable = false)
    @NotNull(message = "O nome da cidade não pode estar vazio")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "O código do ibge é obrigatório")
    private int ibge;

    //MUITAS CIDADES PARA UM ESTADO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estadoId", nullable = false)
    private Estado estadoId;

    @OneToMany(mappedBy = "empresaProdutores", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoProdutor> enderecoProdutores = new ArrayList<>();

    @OneToMany(mappedBy = "enderecoPrefeitura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoPrefeitura> enderecoPrefeitura = new ArrayList<>();

    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuarioSuporteId", nullable = false, updatable = false)
    private UsuarioSuporte usuarioSuporteId;

}
