package br.com.iagoreis.segtaf.database.gateway;

import br.com.iagoreis.segtaf.application.business.exception.ProdutoNaoEncontradoException;
import br.com.iagoreis.segtaf.application.business.gateway.ExcluirProdutoPorId;
import br.com.iagoreis.segtaf.database.ProdutoRepository;
import org.springframework.stereotype.Component;

@Component
public class ExcluirProdutoPorIdImpl implements ExcluirProdutoPorId {

    private final ProdutoRepository produtoRepository;

    public ExcluirProdutoPorIdImpl(final ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void excluir(final Long id) {

        produtoRepository
            .findById(id)
            .orElseThrow(() -> new ProdutoNaoEncontradoException());

        produtoRepository.deleteById(id);
    }

}
