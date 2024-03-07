package br.com.iagoreis.segtaf.domain.business.rules;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraTarifaCategoriaAutoTest {

    private final CalculadoraTarifaCategoria calculadora = new CalculadoraTarifaCategoriaAuto();

    private final Categoria categoria = Categoria.AUTO;

    @Test
    void calculoValorTarifaDeveEstarCorreto() {

        BigDecimal precoBase;
        BigDecimal precoTarifadoEsperado;
        BigDecimal precoTarifadoCalculado;

        precoBase = new BigDecimal("100.00");
        precoTarifadoEsperado = new BigDecimal("110.50");
        precoTarifadoCalculado = calculadora.calcularPrecoTarifado(categoria, precoBase);
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);

        precoBase = new BigDecimal("1000.00");
        precoTarifadoEsperado = new BigDecimal("1105.00");
        precoTarifadoCalculado = calculadora.calcularPrecoTarifado(categoria, precoBase);
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);

        precoBase = new BigDecimal("875.48");
        precoTarifadoEsperado = new BigDecimal("967.41");
        precoTarifadoCalculado = calculadora.calcularPrecoTarifado(categoria, precoBase);
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
    }

}
