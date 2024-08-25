package br.com.estudo.calculos.api_eco_feira.model.prefeitura;

import br.com.estudo.calculos.api_eco_feira.model.Endereco;
import br.com.estudo.calculos.api_eco_feira.model.central.Demanda;
import br.com.estudo.calculos.api_eco_feira.model.central.Demanda_Produto_Associados;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prefeitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrefeitura;

    @Column(nullable = false)
    @NotNull(message = "O nome fantasia é obrigatório")
    private String nomeFantasia;

    @Column(nullable = false)
    @NotNull(message = "A razão social é obrigatória")
    private String razaoSocial;

    @Column(nullable = false)
    @NotNull(message = "O cnpj é obrigatório")
    private String cnpj;

    @OneToMany(mappedBy = "prefeitura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos;

    @OneToMany(mappedBy = "prefeitura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @Column(nullable = true)
    private String logoUrl;

    @OneToMany(mappedBy = "prefeitura")
    private List<Demanda> demandas;

}
