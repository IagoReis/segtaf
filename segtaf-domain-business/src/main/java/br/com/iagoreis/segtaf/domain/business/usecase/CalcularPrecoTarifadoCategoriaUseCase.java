package br.com.iagoreis.segtaf.domain.business.usecase;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;

import java.math.BigDecimal;

public interface CalcularPrecoTarifadoCategoriaUseCase {

    BigDecimal execute(Categoria categoria, BigDecimal precoBase);

}
