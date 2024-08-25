package br.com.api_eco_feira.model.produtor;

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
public class Produto_Promocao_Associados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto_Promocao_Associado;

    @ManyToOne
    @JoinColumn(name = "empresaId", nullable = false)
    private Empresa empresaId;

    @ManyToOne
    @JoinColumn(name = "produtoProdutorId", nullable = false)
    private ProdutoProdutor produtoProdutor;

    @ManyToOne
    @JoinColumn(name = "promocaoId", nullable = false)
    private Promocao promocao;

    @Column(nullable = false)
    private boolean ativo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime dataHoraAlteracao = LocalDateTime.now();

}
