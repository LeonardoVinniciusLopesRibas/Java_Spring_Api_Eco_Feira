package br.com.api_eco_feira.model.central;

import br.com.api_eco_feira.model.produtor.Empresa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DemandaAssociadoProdutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "demandaId", nullable = false)
    private Demanda_Produto_Associados demandaProdutoAssociados;

    private double quantidadeAtendida;

    @ManyToOne
    @JoinColumn(name = "produtorId", nullable = false)
    private Empresa empresa;

}
