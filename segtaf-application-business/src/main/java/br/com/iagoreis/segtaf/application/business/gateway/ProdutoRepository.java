package br.com.iagoreis.segtaf.application.business.gateway;

import br.com.iagoreis.segtaf.domain.business.entity.Produto;

public interface ProdutoRepository {

    Produto cadastrar(Produto produto);

    Produto alterar(Produto produto);

}
