package br.com.iagoreis.segtaf.domain.business.rules;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import br.com.iagoreis.segtaf.domain.business.exception.CalculadoraTarifaCategoriaArgumentosInvalidosException;
import br.com.iagoreis.segtaf.domain.business.exception.CalculadoraTarifaCategoriaNaoEncontradaException;
import br.com.iagoreis.segtaf.domain.business.exception.CalculadoraTarifaCategoriaNullException;
import br.com.iagoreis.segtaf.domain.business.exception.CalculadorasTarifaCategoriaNaoInformadasException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class CalculadoraTarifaCategoria {

    public static final int CEM_POR_CENTO = 100;

    private final Categoria categoria;

    private final Double iof;

    private final Double pis;

    private final Double cofins;

    private CalculadoraTarifaCategoria proximaCalculadora;

    public CalculadoraTarifaCategoria(
        final Categoria categoria,
        final Double iof,
        final Double pis,
        final Double cofins
    ) {

        if (categoria == null) {
            throw new CalculadoraTarifaCategoriaArgumentosInvalidosException("Categoria nao pode ser null");
        }

        if (iof == null) {
            throw new CalculadoraTarifaCategoriaArgumentosInvalidosException("IOF nao pode ser null");
        }

        if (pis == null) {
            throw new CalculadoraTarifaCategoriaArgumentosInvalidosException("PIS nao pode ser null");
        }

        if (cofins == null) {
            throw new CalculadoraTarifaCategoriaArgumentosInvalidosException("COFINS nao pode ser null");
        }

        this.categoria = categoria;
        this.iof = iof;
        this.pis = pis;
        this.cofins = cofins;
    }

    public static CalculadoraTarifaCategoria construirCorrenteCalculadoras(
        final CalculadoraTarifaCategoria... calculadoras
    ) {

        if (calculadoras == null) {
            throw new CalculadorasTarifaCategoriaNaoInformadasException("Nao informado calculadoras de tarifa");
        }

        for (int i = 1; i < calculadoras.length; i++) {

            if (calculadoras[i] == null) {
                throw new CalculadoraTarifaCategoriaNullException("Calculadora de tarifa nao pode ser null");
            }

            calculadoras[i-1].proximaCalculadora = calculadoras[i];
        }

        return calculadoras[0];
    }

    public BigDecimal calcularPrecoTarifado(final Categoria categoria, final BigDecimal precoBase) {

        if (!consigoCalcularCategoria(categoria)) {

            if (proximaCalculadora == null) {
                throw new CalculadoraTarifaCategoriaNaoEncontradaException(String.format("Nao encontrado calculadora de tarifa para a categoria %s", categoria));
            }

            return proximaCalculadora.calcularPrecoTarifado(categoria, precoBase);
        }

        return precoBase
            .add(calcularValorTarifa(precoBase, iof))
            .add(calcularValorTarifa(precoBase, pis))
            .add(calcularValorTarifa(precoBase, cofins))
            .setScale(2, RoundingMode.HALF_EVEN);
    }

    private boolean consigoCalcularCategoria(final Categoria categoriaVerificacao) {
        return categoria.equals(categoriaVerificacao);
    }

    private BigDecimal calcularValorTarifa(final BigDecimal valorBase, final Double porcentagem) {
        return valorBase
            .divide(BigDecimal.valueOf(CEM_POR_CENTO))
            .multiply(BigDecimal.valueOf(porcentagem));
    }

}
