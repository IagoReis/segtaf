package br.com.iagoreis.segtaf.domain.business.rules;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;

public class CalculadoraTarifaCategoriaPatrimonial extends CalculadoraTarifaCategoria {

    public CalculadoraTarifaCategoriaPatrimonial() {
        super(Categoria.PATRIMONIAL, 5.0d, 3.0d, 0.0d);
    }

}
