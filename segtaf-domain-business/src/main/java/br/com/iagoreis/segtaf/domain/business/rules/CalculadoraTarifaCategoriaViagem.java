package br.com.iagoreis.segtaf.domain.business.rules;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;

public class CalculadoraTarifaCategoriaViagem extends CalculadoraTarifaCategoria {

    public CalculadoraTarifaCategoriaViagem() {
        super(Categoria.VIAGEM, 2.0d, 4.0d, 1.0d);
    }

}
