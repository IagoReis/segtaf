package br.com.iagoreis.segtaf.application.business.usecase;

import br.com.iagoreis.segtaf.domain.business.entity.Produto;

public interface CadastrarProdutoUseCase {

    Produto execute(final Produto produto);

}
