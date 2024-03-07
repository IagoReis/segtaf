package br.com.iagoreis.segtaf.domain.business.rules;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;

public class CalculadoraTarifaCategoriaVida extends CalculadoraTarifaCategoria {

    public CalculadoraTarifaCategoriaVida() {
        super(Categoria.VIDA, 1.0d, 2.2d, 0.0d);
    }

}
