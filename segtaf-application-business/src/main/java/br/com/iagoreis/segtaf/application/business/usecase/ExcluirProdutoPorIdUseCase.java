package br.com.iagoreis.segtaf.application.business.usecase;

import br.com.iagoreis.segtaf.domain.business.entity.Produto;

public interface ExcluirProdutoPorIdUseCase {

    void execute(final Long id);

}
