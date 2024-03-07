package br.com.iagoreis.segtaf.domain.business.rules;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraTarifaCategoriaPratimonialTest {

    private final CalculadoraTarifaCategoria calculadora = new CalculadoraTarifaCategoriaPatrimonial();

    private final Categoria categoria = Categoria.PATRIMONIAL;

    @Test
    void calculoValorTarifaDeveEstarCorreto() {

        BigDecimal precoBase;
        BigDecimal precoTarifadoEsperado;
        BigDecimal precoTarifadoCalculado;

        precoBase = new BigDecimal("100.00");
        precoTarifadoEsperado = new BigDecimal("108.00");
        precoTarifadoCalculado = calculadora.calcularPrecoTarifado(categoria, precoBase);
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);

        precoBase = new BigDecimal("1000.00");
        precoTarifadoEsperado = new BigDecimal("1080.00");
        precoTarifadoCalculado = calculadora.calcularPrecoTarifado(categoria, precoBase);
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);

        precoBase = new BigDecimal("875.48");
        precoTarifadoEsperado = new BigDecimal("945.52");
        precoTarifadoCalculado = calculadora.calcularPrecoTarifado(categoria, precoBase);
        assertEquals(precoTarifadoEsperado, precoTarifadoCalculado);
    }

}
