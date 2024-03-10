package br.com.iagoreis.segtaf.domain.business.entity;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProdutoTest {

    private final Long id = 1l;
    private final String nome = "Seguro de Vida III";
    private final Categoria categoria = Categoria.VIDA;
    private final BigDecimal precoBase = BigDecimal.valueOf(100.00);
    private final BigDecimal precoTarifado = BigDecimal.valueOf(110.00);

    @Test
    void garantirQueOProdutoEstejaSendoCriadoCorettamentePeloConstrutor() {

        final var produto = new Produto(id, nome, categoria, precoBase, precoTarifado);

        assertNotNull(produto);
        assertEquals(id, produto.getId());
        assertEquals(nome, produto.getNome());
        assertEquals(categoria, produto.getCategoria());
        assertEquals(precoBase, produto.getPrecoBase());
        assertEquals(precoTarifado, produto.getPrecoTarifado());
    }

    @Test
    void garantirQueOsGettersESettersEstejamFuncionando() {

        final var produto = new Produto();

        produto.setId(id);
        produto.setNome(nome);
        produto.setCategoria(categoria);
        produto.setPrecoBase(precoBase);
        produto.setPrecoTarifado(precoTarifado);

        assertNotNull(produto);
        assertEquals(id, produto.getId());
        assertEquals(nome, produto.getNome());
        assertEquals(categoria, produto.getCategoria());
        assertEquals(precoBase, produto.getPrecoBase());
        assertEquals(precoTarifado, produto.getPrecoTarifado());
    }

    @Test
    void garantirQueOToStringEstejaSendoRetornadoComOsValoresCorretos() {

        final var produto = new Produto(id, nome, categoria, precoBase, precoTarifado);

        assertEquals(gerarToString(produto), produto.toString());
    }

    private String gerarToString(final Produto produto) {
        return "Produto(" +
            "id=" + produto.getId() +
            ", nome='" + produto.getNome() + '\'' +
            ", categoria=" + produto.getCategoria() +
            ", precoBase=" + produto.getPrecoBase() +
            ", precoTarifado=" + produto.getPrecoTarifado() +
            ')';
    }

}
