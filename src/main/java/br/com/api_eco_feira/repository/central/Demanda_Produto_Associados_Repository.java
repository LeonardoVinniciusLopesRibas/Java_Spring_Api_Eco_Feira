package br.com.api_eco_feira.repository.central;

import br.com.api_eco_feira.model.central.Demanda_Produto_Associados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Demanda_Produto_Associados_Repository extends JpaRepository<Demanda_Produto_Associados, Long> {
}
