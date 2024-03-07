package br.com.iagoreis.segtaf.mock;

import br.com.iagoreis.segtaf.application.business.gateway.ProdutoRepository;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;

import java.util.Random;

public class ProdutoRepositoryMock implements ProdutoRepository {

    @Override
    public Produto cadastrar(final Produto produto) {

        final var produtoCadastrado = new Produto(
            new Random().nextLong(),
            produto.getNome(),
            produto.getCategoria(),
            produto.getPrecoBase(),
            produto.getPrecoTarifado()
        );

        return produtoCadastrado;
    }

    @Override
    public Produto alterar(final Produto produto) {

        final var produtoAlterado = new Produto(
            produto.getId(),
            produto.getNome(),
            produto.getCategoria(),
            produto.getPrecoBase(),
            produto.getPrecoTarifado()
        );

        return produtoAlterado;
    }

}
