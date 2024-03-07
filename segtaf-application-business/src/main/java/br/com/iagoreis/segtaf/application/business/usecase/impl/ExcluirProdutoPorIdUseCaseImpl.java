package br.com.iagoreis.segtaf.application.business.usecase.impl;

import br.com.iagoreis.segtaf.application.business.gateway.ExcluirProdutoPorId;
import br.com.iagoreis.segtaf.application.business.usecase.ExcluirProdutoPorIdUseCase;

public class ExcluirProdutoPorIdUseCaseImpl implements ExcluirProdutoPorIdUseCase {

    private final ExcluirProdutoPorId excluirProdutoPorId;

    public ExcluirProdutoPorIdUseCaseImpl(final ExcluirProdutoPorId excluirProdutoPorId) {
        this.excluirProdutoPorId = excluirProdutoPorId;
    }

    @Override
    public void execute(final Long id) {
        excluirProdutoPorId.excluir(id);
    }

}
