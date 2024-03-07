package br.com.iagoreis.segtaf.database;

import br.com.iagoreis.segtaf.database.model.ProdutoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<ProdutoEntity, Long> {
    
}
