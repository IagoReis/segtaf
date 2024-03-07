package br.com.iagoreis.segtaf.application.business.usecase.impl;

import br.com.iagoreis.segtaf.application.business.gateway.AlterarProduto;
import br.com.iagoreis.segtaf.application.business.usecase.AlterarProdutoUseCase;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import br.com.iagoreis.segtaf.domain.business.usecase.CalcularPrecoTarifadoCategoriaUseCase;
import br.com.iagoreis.segtaf.domain.business.usecase.impl.CalcularPrecoTarifadoCategoriaUseCaseImpl;
import br.com.iagoreis.segtaf.mock.AlterarProdutoMock;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlterarProdutoUseCaseImplTest {

    private CalcularPrecoTarifadoCategoriaUseCase calcularPrecoTarifadoCategoriaUseCase = new CalcularPrecoTarifadoCategoriaUseCaseImpl();

    final AlterarProduto alterarProduto = new AlterarProdutoMock();

    private AlterarProdutoUseCase alterarProdutoUseCase = new AlterarProdutoUseCaseImpl(
        calcularPrecoTarifadoCategoriaUseCase,
        alterarProduto
    );

    @Test
    void garantirQueOProdutoEstejaSendoAlteradoComSucesso() {

        final var categoria = Categoria.VIDA;
        final var precoBase = new BigDecimal("100.00");
        final var precoTarifado = calcularPrecoTarifadoCategoriaUseCase.execute(categoria, precoBase);

        final var produto = new Produto();
        produto.setId(123456879l);
        produto.setNome("Seguro de Vida Teste I");
        produto.setCategoria(categoria);
        produto.setPrecoBase(precoBase);
        produto.setPrecoTarifado(precoTarifado);

        final var produtoAlterado = alterarProdutoUseCase.execute(produto.getId(), produto);

        assertEquals(produto.getId(), produtoAlterado.getId());
        assertEquals(produto.getNome(), produtoAlterado.getNome());
        assertEquals(produto.getCategoria(), produtoAlterado.getCategoria());
        assertEquals(produto.getPrecoBase(), produtoAlterado.getPrecoBase());
        assertEquals(produto.getPrecoTarifado(), produtoAlterado.getPrecoTarifado());
    }

}
