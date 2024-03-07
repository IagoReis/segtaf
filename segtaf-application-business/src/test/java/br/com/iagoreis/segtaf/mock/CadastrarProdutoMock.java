package br.com.iagoreis.segtaf.mock;

import br.com.iagoreis.segtaf.application.business.gateway.CadastrarProduto;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;

import java.util.Random;

public class CadastrarProdutoMock implements CadastrarProduto {

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

}
