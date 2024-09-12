package br.com.api_eco_feira.repository.produtor;

import br.com.api_eco_feira.model.produtor.ProdutoProdutor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoProdutorRepository extends JpaRepository<ProdutoProdutor, Long> {

    boolean existsByGrupoProdutos_IdGrupo(Long id);

    List<ProdutoProdutor> findProdutoProdutorByAtivoTrue(Sort sort);
}
