package br.com.iagoreis.segtaf.repository.model;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table
public class ProdutoEntity {

    @Id
    private Long id;

    private String nome;

    private Categoria categoria;

    private BigDecimal precoBase;

    private BigDecimal precoTarifado;

}
