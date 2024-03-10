package br.com.iagoreis.segtaf.domain.business.rules;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import br.com.iagoreis.segtaf.domain.business.exception.CalculadoraTarifaCategoriaArgumentosInvalidosException;
import br.com.iagoreis.segtaf.domain.business.exception.CalculadoraTarifaCategoriaNaoEncontradaException;
import br.com.iagoreis.segtaf.domain.business.exception.CalculadoraTarifaCategoriaNullException;
import br.com.iagoreis.segtaf.domain.business.exception.CalculadorasTarifaCategoriaNaoInformadasException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraTarifaCategoriaTest {

    @Test
    void garantirQueConstruirCorrenteCalculadorasFuncionaComTodasCategorias() {

        final var correnteCalculadoras = CalculadoraTarifaCategoria.construirCorrenteCalculadoras(
            new CalculadoraTarifaCategoriaVida(),
            new CalculadoraTarifaCategoriaAuto(),
            new CalculadoraTarifaCategoriaViagem(),
            new CalculadoraTarifaCategoriaResidencial(),
            new CalculadoraTarifaCategoriaPatrimonial()
        );

        final var precoBase = new BigDecimal("875.48");

        assertDoesNotThrow(() -> {

            final var precoTarifadoCalculado = correnteCalculadoras.calcularPrecoTarifado(Categoria.VIDA, precoBase);
            assertNotNull(precoTarifadoCalculado);

            final var precoTarifadoEsperado = new BigDecimal("903.50");
            assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
        });

        assertDoesNotThrow(() -> {

            final var precoTarifadoCalculado = correnteCalculadoras.calcularPrecoTarifado(Categoria.AUTO, precoBase);
            assertNotNull(precoTarifadoCalculado);

            final var precoTarifadoEsperado = new BigDecimal("967.41");
            assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
        });

        assertDoesNotThrow(() -> {

            final var precoTarifadoCalculado = correnteCalculadoras.calcularPrecoTarifado(Categoria.VIAGEM, precoBase);
            assertNotNull(precoTarifadoCalculado);

            final var precoTarifadoEsperado = new BigDecimal("936.76");
            assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
        });

        assertDoesNotThrow(() -> {

            final var precoTarifadoCalculado = correnteCalculadoras.calcularPrecoTarifado(Categoria.RESIDENCIAL, precoBase);
            assertNotNull(precoTarifadoCalculado);

            final var precoTarifadoEsperado = new BigDecimal("936.76");
            assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
        });

        assertDoesNotThrow(() -> {

            final var precoTarifadoCalculado = correnteCalculadoras.calcularPrecoTarifado(Categoria.PATRIMONIAL, precoBase);
            assertNotNull(precoTarifadoCalculado);

            final var precoTarifadoEsperado = new BigDecimal("945.52");
            assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
        });
    }

    @Test
    void garantirQueConstruirCorrenteCalculadorasTemQueReceberAoMenosUmaCalculadora() {
        assertThrows(CalculadorasTarifaCategoriaNaoInformadasException.class, () -> {
            CalculadoraTarifaCategoria.construirCorrenteCalculadoras(null);
        });
    }

    @Test
    void garantirQueConstruirCorrenteCalculadorasNaoPodeReceberNenhumaCalculadoraNula() {
        assertThrows(CalculadoraTarifaCategoriaNullException.class, () -> {
            CalculadoraTarifaCategoria.construirCorrenteCalculadoras(
                new CalculadoraTarifaCategoriaVida(),
                new CalculadoraTarifaCategoriaAuto(),
                null
            );
        });
    }

    @Test
    void garantirQueEstoureUmaExcecaoCasoNaoSejaEncontradoUmaCalculadoraParaACategoria() {
        assertThrows(CalculadoraTarifaCategoriaNaoEncontradaException.class, () -> {

            final var calculadoras = CalculadoraTarifaCategoria.construirCorrenteCalculadoras(
                new CalculadoraTarifaCategoriaVida(),
                new CalculadoraTarifaCategoriaAuto(),
                new CalculadoraTarifaCategoriaViagem()
            );

            calculadoras.calcularPrecoTarifado(Categoria.RESIDENCIAL, BigDecimal.TEN);
        });
    }

    @Test
    void garantirQueEstoureUmaExcecaoCasoACalculadoraSejaCriadaComDadosInvalidos() {

        final var categoria = Categoria.VIDA;
        final var iof = 1.00d;
        final var pis = 2.00d;
        final var cofins = 3.00d;

        assertThrows(CalculadoraTarifaCategoriaArgumentosInvalidosException.class, () -> {
            new CalculadoraTarifaCategoria(null, iof, pis, cofins) {};
        });

        assertThrows(CalculadoraTarifaCategoriaArgumentosInvalidosException.class, () -> {
            new CalculadoraTarifaCategoria(categoria, null, pis, cofins) {};
        });

        assertThrows(CalculadoraTarifaCategoriaArgumentosInvalidosException.class, () -> {
            new CalculadoraTarifaCategoria(categoria, iof, null, cofins) {};
        });

        assertThrows(CalculadoraTarifaCategoriaArgumentosInvalidosException.class, () -> {
            new CalculadoraTarifaCategoria(categoria, iof, pis, null) {};
        });

    }

}
