package br.com.iagoreis.segtaf.application.business.usecase.impl;

import br.com.iagoreis.segtaf.application.business.gateway.ProdutoRepository;
import br.com.iagoreis.segtaf.application.business.usecase.CadastrarProdutoUseCase;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.domain.business.usecase.CalcularPrecoTarifadoCategoriaUseCase;

public class CadastrarProdutoUseCaseImpl implements CadastrarProdutoUseCase {

    private final CalcularPrecoTarifadoCategoriaUseCase calcularPrecoTarifadoCategoriaUseCase;

    private final ProdutoRepository produtoRepository;

    public CadastrarProdutoUseCaseImpl(
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

        produto.setId(null);
        produto.setPrecoTarifado(precoTarifado);

        final var produtoCadastrado = produtoRepository.cadastrar(produto);

        return produtoCadastrado;
    }

}
