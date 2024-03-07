package br.com.iagoreis.segtaf.domain.business.rules;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraTarifaCategoriaViagemTest {

    private final CalculadoraTarifaCategoria calculadora = new CalculadoraTarifaCategoriaViagem();

    private final Categoria categoria = Categoria.VIAGEM;

    @Test
    void calculoValorTarifaDeveEstarCorreto() {

        BigDecimal precoBase;
        BigDecimal precoTarifadoEsperado;
        BigDecimal precoTarifadoCalculado;

        precoBase = new BigDecimal("100.00");
        precoTarifadoEsperado = new BigDecimal("107.00");
        precoTarifadoCalculado = calculadora.calcularPrecoTarifado(categoria, precoBase);
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);

        precoBase = new BigDecimal("1000.00");
        precoTarifadoEsperado = new BigDecimal("1070.00");
        precoTarifadoCalculado = calculadora.calcularPrecoTarifado(categoria, precoBase);
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);

        precoBase = new BigDecimal("875.48");
        precoTarifadoEsperado = new BigDecimal("936.76");
        precoTarifadoCalculado = calculadora.calcularPrecoTarifado(categoria, precoBase);
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
    }

}
