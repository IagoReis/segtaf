package br.com.iagoreis.segtaf.repository;

import br.com.iagoreis.segtaf.repository.model.ProdutoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<ProdutoEntity, Long> {
    
}
