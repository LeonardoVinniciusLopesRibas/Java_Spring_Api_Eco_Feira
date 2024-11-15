package br.com.api_eco_feira.repository.central;

import br.com.api_eco_feira.model.central.DemandaQuantidadeAssociaProdutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandaQuantidadeAssociaProdutorRepository extends JpaRepository<DemandaQuantidadeAssociaProdutor, Long> {

    List<DemandaQuantidadeAssociaProdutor> findAllByDemanda_IdDemandaAndAndDemandaProdutoAssociados_IdDemandaProduto(Long idDemanda, Long idProdutoAssociados);

}
