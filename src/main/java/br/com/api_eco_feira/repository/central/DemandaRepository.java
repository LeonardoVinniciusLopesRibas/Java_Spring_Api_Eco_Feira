package br.com.api_eco_feira.repository.central;

import br.com.api_eco_feira.model.central.Demanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Long> {
}
