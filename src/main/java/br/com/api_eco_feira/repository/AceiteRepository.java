package br.com.api_eco_feira.repository;

import br.com.api_eco_feira.model.Aceite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AceiteRepository extends JpaRepository<Aceite, Long> {
}
