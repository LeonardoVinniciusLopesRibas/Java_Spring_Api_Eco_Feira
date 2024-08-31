package br.com.api_eco_feira.repository.produtor;

import br.com.api_eco_feira.model.produtor.GrupoProdutos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoProdutosRepository extends JpaRepository<GrupoProdutos,Long> {
}
