package br.com.iagoreis.segtaf.database.gateway;

import br.com.iagoreis.segtaf.application.business.gateway.CadastrarProduto;
import br.com.iagoreis.segtaf.database.ProdutoRepository;
import br.com.iagoreis.segtaf.database.mapper.ProdutoEntityMapper;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class CadastrarProdutoImpl implements CadastrarProduto {

    private final ProdutoRepository produtoRepository;

    private final ProdutoEntityMapper produtoEntityMapper;

    public CadastrarProdutoImpl(
        final ProdutoRepository produtoRepository,
        final ProdutoEntityMapper produtoEntityMapper
    ) {
        this.produtoRepository = produtoRepository;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    @Override
    public Produto cadastrar(final Produto produto) {

        final var produtoEntity = produtoEntityMapper.mapFrom(produto);

        final var produtoEntityCadastrado = produtoRepository.save(produtoEntity);

        final var produtoCadastrado = produtoEntityMapper.mapFrom(produtoEntityCadastrado);

        return produtoCadastrado;
    }

}
