package br.com.iagoreis.segtaf.application.business.usecase.impl;

import br.com.iagoreis.segtaf.application.business.exception.ProdutoNaoEncontradoException;
import br.com.iagoreis.segtaf.application.business.gateway.BuscarProdutoPorId;
import br.com.iagoreis.segtaf.application.business.usecase.BuscarProdutoPorIdUseCase;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Random;

class BuscarProdutoPorIdUseCaseImplTest {

    private static final Long ID_PRODUTO = new Random().nextLong();

    private final BuscarProdutoPorId buscarProdutoPorId = new BuscarProdutoPorId() {
        @Override
        public Produto buscar(final Long id) {
            return new Produto(ID_PRODUTO, "", Categoria.VIDA, BigDecimal.TEN, BigDecimal.TEN);
        }
    };

    private final BuscarProdutoPorId buscarProdutoPorIdException = new BuscarProdutoPorId() {
        @Override
        public Produto buscar(final Long id) {
            throw new ProdutoNaoEncontradoException();
        }
    };

    private final BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase = new BuscarProdutoPorIdUseCaseImpl(buscarProdutoPorId);

    private final BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCaseException = new BuscarProdutoPorIdUseCaseImpl(buscarProdutoPorIdException);

    @Test
    void garantirQueOProdutoEstejaSendoBuscadoComSucesso() {

        final var produto = buscarProdutoPorIdUseCase.execute(ID_PRODUTO);

        Assertions.assertNotNull(produto);
        Assertions.assertNotNull(produto.getId());
        Assertions.assertNotNull(produto.getNome());
        Assertions.assertNotNull(produto.getCategoria());
        Assertions.assertNotNull(produto.getPrecoBase());
        Assertions.assertNotNull(produto.getPrecoTarifado());
    }

    @Test
    void garantirQueEstoureExcessaoCasoOProdutoNaoSejaEncontrado() {
        Assertions.assertThrows(ProdutoNaoEncontradoException.class, () -> {
            buscarProdutoPorIdUseCaseException.execute(ID_PRODUTO);
        });
    }

}
