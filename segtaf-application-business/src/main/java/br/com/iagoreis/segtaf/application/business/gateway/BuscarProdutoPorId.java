package br.com.iagoreis.segtaf.application.business.gateway;

import br.com.iagoreis.segtaf.domain.business.entity.Produto;

public interface BuscarProdutoPorId {

    Produto buscar(Long id);

}
