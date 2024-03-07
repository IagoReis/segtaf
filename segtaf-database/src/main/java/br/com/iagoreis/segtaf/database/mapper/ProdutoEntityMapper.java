package br.com.iagoreis.segtaf.database.mapper;

import br.com.iagoreis.segtaf.database.model.ProdutoEntity;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoEntityMapper {

    ProdutoEntity mapFrom(Produto produto);

    Produto mapFrom(ProdutoEntity produtoEntity);
}
