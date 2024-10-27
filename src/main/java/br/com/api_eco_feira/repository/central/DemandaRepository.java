package br.com.api_eco_feira.repository.central;

import br.com.api_eco_feira.enumerador.StatusDemanda;
import br.com.api_eco_feira.model.central.Demanda;
import br.com.api_eco_feira.model.prefeitura.Prefeitura;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Long> {

    List<Demanda> findByPrefeitura(Prefeitura prefeitura, Sort sort);

    List<Demanda> findByPrefeituraAndStatusDemanda(Prefeitura prefeitura, StatusDemanda statusDemanda, Sort sort);

}
