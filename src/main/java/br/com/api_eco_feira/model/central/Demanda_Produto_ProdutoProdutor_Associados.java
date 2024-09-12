package br.com.api_eco_feira.model.central;

import br.com.api_eco_feira.auth.Usuario;
import br.com.api_eco_feira.model.prefeitura.ProdutoPrefeitura;
import br.com.api_eco_feira.model.produtor.Empresa;
import br.com.api_eco_feira.model.produtor.ProdutoProdutor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Demanda_Produto_ProdutoProdutor_Associados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemandaProdutoProdutoProdutorAssociados;

    @ManyToOne
    @JoinColumn(name = "demandaProdutosId", nullable = false)
    private Demanda_Produto_Associados demandaProdutoAssociados;

    @ManyToOne
    @JoinColumn(name = "produtoProdutorId", nullable = false)
    private ProdutoProdutor produtoProdutor;

    @NotNull(message = "Quantidade é obrigatória")
    @Column(nullable = false)
    private double quantidade;

    @ManyToOne
    @JoinColumn(name = "empresaId", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuario;

}
