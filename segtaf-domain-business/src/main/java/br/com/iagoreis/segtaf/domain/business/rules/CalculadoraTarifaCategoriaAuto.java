package br.com.iagoreis.segtaf.domain.business.rules;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;

public class CalculadoraTarifaCategoriaAuto extends CalculadoraTarifaCategoria {

    public CalculadoraTarifaCategoriaAuto() {
        super(Categoria.AUTO, 5.5d, 4.0d, 1.0d);
    }

}
