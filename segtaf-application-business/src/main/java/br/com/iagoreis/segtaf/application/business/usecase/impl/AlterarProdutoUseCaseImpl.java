package br.com.iagoreis.segtaf.application.business.usecase.impl;

import br.com.iagoreis.segtaf.application.business.gateway.ProdutoRepository;
import br.com.iagoreis.segtaf.application.business.usecase.AlterarProdutoUseCase;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.domain.business.usecase.CalcularPrecoTarifadoCategoriaUseCase;

public class AlterarProdutoUseCaseImpl implements AlterarProdutoUseCase {

    private final CalcularPrecoTarifadoCategoriaUseCase calcularPrecoTarifadoCategoriaUseCase;

    private final ProdutoRepository produtoRepository;

    public AlterarProdutoUseCaseImpl(
        final CalcularPrecoTarifadoCategoriaUseCase calcularPrecoTarifadoCategoriaUseCase,
        final ProdutoRepository produtoRepository
    ) {
        this.calcularPrecoTarifadoCategoriaUseCase = calcularPrecoTarifadoCategoriaUseCase;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto execute(final Produto produto) {

        final var precoTarifado = calcularPrecoTarifadoCategoriaUseCase.execute(
            produto.getCategoria(),
            produto.getPrecoBase()
        );

        produto.setPrecoTarifado(precoTarifado);

        final var produtoAlterado = produtoRepository.alterar(produto);

        return produtoAlterado;
    }

}
