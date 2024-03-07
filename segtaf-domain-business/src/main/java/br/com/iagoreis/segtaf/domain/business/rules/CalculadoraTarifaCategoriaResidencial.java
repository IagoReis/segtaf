package br.com.iagoreis.segtaf.domain.business.rules;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;

public class CalculadoraTarifaCategoriaResidencial extends CalculadoraTarifaCategoria {

    public CalculadoraTarifaCategoriaResidencial() {
        super(Categoria.RESIDENCIAL, 4.0d, 0.0d, 3.0d);
    }

}
