package br.com.iagoreis.segtaf.domain.business.usecase.impl;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import br.com.iagoreis.segtaf.domain.business.usecase.CalcularPrecoTarifadoCategoriaUseCase;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalcularTarifaCategoriaUseCaseImplTest {

    private final CalcularPrecoTarifadoCategoriaUseCase calcularPrecoTarifadoCategoriaUseCase = new CalcularPrecoTarifadoCategoriaUseCaseImpl();

    final BigDecimal precoBase = new BigDecimal("875.48");

    @Test
    void garantirQueSejaCalculadaCategoriaVida() {

        final var precoTarifadoCalculado = calcularPrecoTarifadoCategoriaUseCase.execute(Categoria.VIDA, precoBase);
        final var precoTarifadoEsperado = new BigDecimal("903.50");
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
    }

    @Test
    void garantirQueSejaCalculadaCategoriaAuto() {

        final var precoTarifadoCalculado = calcularPrecoTarifadoCategoriaUseCase.execute(Categoria.AUTO, precoBase);
        final var precoTarifadoEsperado = new BigDecimal("967.41");
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
    }

    @Test
    void garantirQueSejaCalculadaCategoriaViagem() {

        final var precoTarifadoCalculado = calcularPrecoTarifadoCategoriaUseCase.execute(Categoria.VIAGEM, precoBase);
        final var precoTarifadoEsperado = new BigDecimal("936.76");
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
    }

    @Test
    void garantirQueSejaCalculadaCategoriaResidencial() {

        final var precoTarifadoCalculado = calcularPrecoTarifadoCategoriaUseCase.execute(Categoria.RESIDENCIAL, precoBase);
        final var precoTarifadoEsperado = new BigDecimal("936.76");
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
    }

    @Test
    void garantirQueSejaCalculadaCategoriaPatrimonial() {

        final var precoTarifadoCalculado = calcularPrecoTarifadoCategoriaUseCase.execute(Categoria.PATRIMONIAL, precoBase);
        final var precoTarifadoEsperado = new BigDecimal("945.52");
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
    }

}
