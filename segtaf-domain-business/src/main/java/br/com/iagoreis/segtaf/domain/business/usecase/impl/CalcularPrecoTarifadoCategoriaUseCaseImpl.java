package br.com.iagoreis.segtaf.domain.business.usecase.impl;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import br.com.iagoreis.segtaf.domain.business.rules.CalculadoraTarifaCategoria;
import br.com.iagoreis.segtaf.domain.business.rules.CalculadoraTarifaCategoriaAuto;
import br.com.iagoreis.segtaf.domain.business.rules.CalculadoraTarifaCategoriaPatrimonial;
import br.com.iagoreis.segtaf.domain.business.rules.CalculadoraTarifaCategoriaResidencial;
import br.com.iagoreis.segtaf.domain.business.rules.CalculadoraTarifaCategoriaViagem;
import br.com.iagoreis.segtaf.domain.business.rules.CalculadoraTarifaCategoriaVida;
import br.com.iagoreis.segtaf.domain.business.usecase.CalcularPrecoTarifadoCategoriaUseCase;

import java.math.BigDecimal;

public class CalcularPrecoTarifadoCategoriaUseCaseImpl implements CalcularPrecoTarifadoCategoriaUseCase {

    private final CalculadoraTarifaCategoria correnteCalculadoras = CalculadoraTarifaCategoria.construirCorrenteCalculadoras(
        new CalculadoraTarifaCategoriaVida(),
        new CalculadoraTarifaCategoriaAuto(),
        new CalculadoraTarifaCategoriaViagem(),
        new CalculadoraTarifaCategoriaResidencial(),
        new CalculadoraTarifaCategoriaPatrimonial()
    );

    @Override
    public BigDecimal execute(final Categoria categoria, final BigDecimal precoBase) {
        return correnteCalculadoras.calcularPrecoTarifado(categoria, precoBase);
    }

}
