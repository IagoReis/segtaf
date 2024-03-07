package br.com.iagoreis.segtaf.application.business.usecase.impl;

import br.com.iagoreis.segtaf.application.business.gateway.BuscarProdutoPorId;
import br.com.iagoreis.segtaf.application.business.usecase.BuscarProdutoPorIdUseCase;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;

public class BuscarProdutoPorIdUseCaseImpl implements BuscarProdutoPorIdUseCase {

    private final BuscarProdutoPorId buscarProdutoPorId;

    public BuscarProdutoPorIdUseCaseImpl(final BuscarProdutoPorId buscarProdutoPorId) {
        this.buscarProdutoPorId = buscarProdutoPorId;
    }

    @Override
    public Produto execute(final Long id) {

        final var produto = buscarProdutoPorId.buscar(id);

        return produto;
    }

}
