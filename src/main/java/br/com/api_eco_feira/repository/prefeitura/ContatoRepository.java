package br.com.api_eco_feira.repository.prefeitura;

import br.com.api_eco_feira.model.prefeitura.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
