package br.com.iagoreis.segtaf.database.gateway;

import br.com.iagoreis.segtaf.application.business.exception.ProdutoNaoEncontradoException;
import br.com.iagoreis.segtaf.application.business.gateway.AlterarProduto;
import br.com.iagoreis.segtaf.database.ProdutoRepository;
import br.com.iagoreis.segtaf.database.mapper.ProdutoEntityMapper;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class AlterarProdutoImpl implements AlterarProduto {

    private final ProdutoRepository produtoRepository;

    private final ProdutoEntityMapper produtoEntityMapper;

    public AlterarProdutoImpl(
        final ProdutoRepository produtoRepository,
        final ProdutoEntityMapper produtoEntityMapper
    ) {
        this.produtoRepository = produtoRepository;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    @Override
    public Produto alterar(final Produto produto) {

        produtoRepository
            .findById(produto.getId())
            .orElseThrow(() -> new ProdutoNaoEncontradoException());

        final var produtoEntity = produtoEntityMapper.mapFrom(produto);

        final var produtoEntityCadastrado = produtoRepository.save(produtoEntity);

        final var produtoCadastrado = produtoEntityMapper.mapFrom(produtoEntityCadastrado);

        return produtoCadastrado;
    }

}
