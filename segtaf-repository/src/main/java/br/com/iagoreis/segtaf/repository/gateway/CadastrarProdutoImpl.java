package br.com.iagoreis.segtaf.repository.gateway;

import br.com.iagoreis.segtaf.application.business.gateway.CadastrarProduto;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.repository.ProdutoRepository;
import br.com.iagoreis.segtaf.repository.model.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class CadastrarProdutoImpl implements CadastrarProduto {

    private final ProdutoRepository produtoRepository;

    public CadastrarProdutoImpl(final ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto cadastrar(final Produto produto) {

        final var produtoEntity = new ProdutoEntity();

        final var produtoEntityCadastrado = produtoRepository.save(produtoEntity);

        return null;
    }

}
