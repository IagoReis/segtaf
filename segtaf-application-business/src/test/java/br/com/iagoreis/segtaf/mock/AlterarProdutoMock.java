package br.com.iagoreis.segtaf.mock;

import br.com.iagoreis.segtaf.application.business.gateway.AlterarProduto;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;

public class AlterarProdutoMock implements AlterarProduto {

    @Override
    public Produto alterar(final Long id, final Produto produto) {

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
