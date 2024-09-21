package br.com.api_eco_feira.repository.prefeitura;

import br.com.api_eco_feira.model.prefeitura.Prefeitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrefeituraRepository extends JpaRepository<Prefeitura, Long> {

    boolean existsByCnpj(String cnpj);

}
