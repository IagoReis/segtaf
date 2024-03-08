package br.com.iagoreis.segtaf.application.business.usecase.impl;

import br.com.iagoreis.segtaf.application.business.gateway.ExcluirProdutoPorId;
import br.com.iagoreis.segtaf.application.business.usecase.ExcluirProdutoPorIdUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

class ExcluirProdutoPorIdUseCaseImplTest {

    private static final Long ID_PRODUTO = new Random().nextLong();

    private final ExcluirProdutoPorId excluirProdutoPorId = new ExcluirProdutoPorId() {
        @Override
        public void excluir(final Long id) {}
    };

    private final ExcluirProdutoPorIdUseCase excluirProdutoPorIdUseCase = new ExcluirProdutoPorIdUseCaseImpl(excluirProdutoPorId);

    @Test
    void garantirQueUmProdutoEstejaSendoExcluidoComSucesso() {
        Assertions.assertDoesNotThrow(() -> {
            excluirProdutoPorIdUseCase.execute(ID_PRODUTO);
        });
    }
}
