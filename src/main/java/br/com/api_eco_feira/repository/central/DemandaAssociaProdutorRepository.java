package br.com.api_eco_feira.repository.central;

import br.com.api_eco_feira.dto.demanda.DemandaDtoResponse;
import br.com.api_eco_feira.enumerador.StatusDemanda;
import br.com.api_eco_feira.model.central.DemandaAssociaProdutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandaAssociaProdutorRepository extends JpaRepository<DemandaAssociaProdutor, Long> {

    boolean existsByDemandaIdDemandaAndEmpresaIdEmpresa(Long idDemanda, Long idProdutor);

    List<DemandaAssociaProdutor> findAllByEmpresaIdEmpresaAndDemanda_StatusDemanda(Long idEmpresa, StatusDemanda statusDemanda);


}
