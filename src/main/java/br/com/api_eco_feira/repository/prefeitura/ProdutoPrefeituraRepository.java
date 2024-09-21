package br.com.api_eco_feira.repository.prefeitura;

import br.com.api_eco_feira.model.prefeitura.ProdutoPrefeitura;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoPrefeituraRepository extends JpaRepository<ProdutoPrefeitura, Long> {
    List<ProdutoPrefeitura> findProdutoPrefeituraByAtivoTrue(Sort sort);

    List<ProdutoPrefeitura> findProdutoPrefeituraByAtivoFalse(Sort sort);

}
