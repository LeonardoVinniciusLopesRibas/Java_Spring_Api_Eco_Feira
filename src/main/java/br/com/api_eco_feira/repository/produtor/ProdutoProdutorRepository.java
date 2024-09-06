package br.com.api_eco_feira.repository.produtor;

import br.com.api_eco_feira.model.produtor.ProdutoProdutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoProdutorRepository extends JpaRepository<ProdutoProdutor, Long> {

    boolean existsByGrupoProdutos_IdGrupo(Long id);
}
