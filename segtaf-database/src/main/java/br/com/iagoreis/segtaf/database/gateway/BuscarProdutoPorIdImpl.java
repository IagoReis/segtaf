package br.com.iagoreis.segtaf.database.gateway;

import br.com.iagoreis.segtaf.application.business.exception.ProdutoNaoEncontradoException;
import br.com.iagoreis.segtaf.application.business.gateway.BuscarProdutoPorId;
import br.com.iagoreis.segtaf.database.ProdutoRepository;
import br.com.iagoreis.segtaf.database.mapper.ProdutoEntityMapper;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class BuscarProdutoPorIdImpl implements BuscarProdutoPorId {

    private final ProdutoRepository produtoRepository;

    private final ProdutoEntityMapper produtoEntityMapper;

    public BuscarProdutoPorIdImpl(
        final ProdutoRepository produtoRepository,
        final ProdutoEntityMapper produtoEntityMapper
    ) {
        this.produtoRepository = produtoRepository;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    @Override
    public Produto buscar(final Long id) {

        final var produtoEntityOptional = produtoRepository.findById(id);

        final var produto = produtoEntityOptional
            .map(produtoEntity -> produtoEntityMapper.mapFrom(produtoEntity))
            .orElseThrow(() -> new ProdutoNaoEncontradoException());

        return produto;
    }

}
