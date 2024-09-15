package br.com.api_eco_feira.repository;

import br.com.api_eco_feira.model.Estado;
import br.com.api_eco_feira.model.Pais;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    List<Estado> findAllByPais(Pais pais, Sort sort);
}
