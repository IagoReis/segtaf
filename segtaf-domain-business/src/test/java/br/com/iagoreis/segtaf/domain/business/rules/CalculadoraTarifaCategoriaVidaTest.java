package br.com.iagoreis.segtaf.domain.business.rules;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraTarifaCategoriaVidaTest {

    private final CalculadoraTarifaCategoria calculadora = new CalculadoraTarifaCategoriaVida();

    private final Categoria categoria = Categoria.VIDA;

    @Test
    void calculoValorTarifaDeveEstarCorreto() {

        BigDecimal precoBase;
        BigDecimal precoTarifadoEsperado;
        BigDecimal precoTarifadoCalculado;

        precoBase = new BigDecimal("100.00");
        precoTarifadoEsperado = new BigDecimal("103.20");
        precoTarifadoCalculado = calculadora.calcularPrecoTarifado(categoria, precoBase);
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);

        precoBase = new BigDecimal("1000.00");
        precoTarifadoEsperado = new BigDecimal("1032.00");
        precoTarifadoCalculado = calculadora.calcularPrecoTarifado(categoria, precoBase);
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);

        precoBase = new BigDecimal("875.48");
        precoTarifadoEsperado = new BigDecimal("903.50");
        precoTarifadoCalculado = calculadora.calcularPrecoTarifado(categoria, precoBase);
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
    }

}
