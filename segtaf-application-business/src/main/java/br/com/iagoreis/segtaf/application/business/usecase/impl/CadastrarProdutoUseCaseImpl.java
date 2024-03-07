package br.com.iagoreis.segtaf.application.business.usecase.impl;

import br.com.iagoreis.segtaf.application.business.gateway.CadastrarProduto;
import br.com.iagoreis.segtaf.application.business.usecase.CadastrarProdutoUseCase;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.domain.business.usecase.CalcularPrecoTarifadoCategoriaUseCase;

public class CadastrarProdutoUseCaseImpl implements CadastrarProdutoUseCase {

    private final CalcularPrecoTarifadoCategoriaUseCase calcularPrecoTarifadoCategoriaUseCase;

    private final CadastrarProduto cadastrarProduto;

    public CadastrarProdutoUseCaseImpl(
        final CalcularPrecoTarifadoCategoriaUseCase calcularPrecoTarifadoCategoriaUseCase,
        final CadastrarProduto cadastrarProduto
    ) {
        this.calcularPrecoTarifadoCategoriaUseCase = calcularPrecoTarifadoCategoriaUseCase;
        this.cadastrarProduto = cadastrarProduto;
    }

    @Override
    public Produto execute(final Produto produto) {

        final var precoTarifado = calcularPrecoTarifadoCategoriaUseCase.execute(
            produto.getCategoria(),
            produto.getPrecoBase()
        );

        produto.setId(null);
        produto.setPrecoTarifado(precoTarifado);

        final var produtoCadastrado = cadastrarProduto.cadastrar(produto);

        return produtoCadastrado;
    }

}
