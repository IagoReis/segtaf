package br.com.iagoreis.segtaf.repository.gateway;

import br.com.iagoreis.segtaf.application.business.gateway.AlterarProduto;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

@Component
public class AlterarProdutoImpl implements AlterarProduto {

    private final ProdutoRepository produtoRepository;

    public AlterarProdutoImpl(final ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto alterar(final Produto produto) {

        return null;
    }

}
