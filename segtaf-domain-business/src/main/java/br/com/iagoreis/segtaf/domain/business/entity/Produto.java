package br.com.iagoreis.segtaf.domain.business.entity;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;

import java.math.BigDecimal;

public class Produto {

    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal precoBase;
    private BigDecimal precoTarifado;

}
