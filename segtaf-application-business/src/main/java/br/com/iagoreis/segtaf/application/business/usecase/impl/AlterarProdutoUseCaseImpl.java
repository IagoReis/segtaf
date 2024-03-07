package br.com.iagoreis.segtaf.application.business.usecase.impl;

import br.com.iagoreis.segtaf.application.business.gateway.AlterarProduto;
import br.com.iagoreis.segtaf.application.business.usecase.AlterarProdutoUseCase;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.domain.business.usecase.CalcularPrecoTarifadoCategoriaUseCase;

public class AlterarProdutoUseCaseImpl implements AlterarProdutoUseCase {

    private final CalcularPrecoTarifadoCategoriaUseCase calcularPrecoTarifadoCategoriaUseCase;

    private final AlterarProduto alterarProduto;

    public AlterarProdutoUseCaseImpl(
        final CalcularPrecoTarifadoCategoriaUseCase calcularPrecoTarifadoCategoriaUseCase,
        final AlterarProduto alterarProduto
    ) {
        this.calcularPrecoTarifadoCategoriaUseCase = calcularPrecoTarifadoCategoriaUseCase;
        this.alterarProduto = alterarProduto;
    }

    @Override
    public Produto execute(final Long id, final Produto produto) {

        final var precoTarifado = calcularPrecoTarifadoCategoriaUseCase.execute(
            produto.getCategoria(),
            produto.getPrecoBase()
        );

        produto.setPrecoTarifado(precoTarifado);

        final var produtoAlterado = alterarProduto.alterar(id, produto);

        return produtoAlterado;
    }

}
