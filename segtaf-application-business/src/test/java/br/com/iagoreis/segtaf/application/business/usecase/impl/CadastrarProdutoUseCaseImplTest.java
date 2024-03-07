package br.com.iagoreis.segtaf.application.business.usecase.impl;

import br.com.iagoreis.segtaf.application.business.gateway.CadastrarProduto;
import br.com.iagoreis.segtaf.application.business.usecase.CadastrarProdutoUseCase;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import br.com.iagoreis.segtaf.domain.business.usecase.CalcularPrecoTarifadoCategoriaUseCase;
import br.com.iagoreis.segtaf.domain.business.usecase.impl.CalcularPrecoTarifadoCategoriaUseCaseImpl;
import br.com.iagoreis.segtaf.mock.CadastrarProdutoMock;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CadastrarProdutoUseCaseImplTest {

    private CalcularPrecoTarifadoCategoriaUseCase calcularPrecoTarifadoCategoriaUseCase = new CalcularPrecoTarifadoCategoriaUseCaseImpl();

    final CadastrarProduto cadastrarProduto = new CadastrarProdutoMock();

    private CadastrarProdutoUseCase cadastrarProdutoUseCase = new CadastrarProdutoUseCaseImpl(
        calcularPrecoTarifadoCategoriaUseCase,
        cadastrarProduto
    );

    @Test
    void garantirQueOProdutoEstejaSendoCadastradoComSucesso() {

        final var categoria = Categoria.VIDA;
        final var precoBase = new BigDecimal("100.00");
        final var precoTarifado = calcularPrecoTarifadoCategoriaUseCase.execute(categoria, precoBase);

        final var produto = new Produto();
        produto.setId(123456879l);
        produto.setNome("Seguro de Vida Teste I");
        produto.setCategoria(categoria);
        produto.setPrecoBase(precoBase);
        produto.setPrecoTarifado(precoTarifado);

        final var produtoCadastrado = cadastrarProdutoUseCase.execute(produto);

        assertNotEquals(produto.getId(), produtoCadastrado.getId());
        assertEquals(produto.getNome(), produtoCadastrado.getNome());
        assertEquals(produto.getCategoria(), produtoCadastrado.getCategoria());
        assertEquals(produto.getPrecoBase(), produtoCadastrado.getPrecoBase());
        assertEquals(produto.getPrecoTarifado(), produtoCadastrado.getPrecoTarifado());
    }

}
