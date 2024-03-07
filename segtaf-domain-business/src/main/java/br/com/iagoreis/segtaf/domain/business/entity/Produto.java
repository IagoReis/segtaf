package br.com.iagoreis.segtaf.domain.business.entity;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;

import java.math.BigDecimal;

public class Produto {

    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal precoBase;
    private BigDecimal precoTarifado;

    public Produto() { }

    public Produto(
        final Long id,
        final String nome,
        final Categoria categoria,
        final BigDecimal precoBase,
        final BigDecimal precoTarifado
    ) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.precoBase = precoBase;
        this.precoTarifado = precoTarifado;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(final Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(final BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public BigDecimal getPrecoTarifado() {
        return precoTarifado;
    }

    public void setPrecoTarifado(final BigDecimal precoTarifado) {
        this.precoTarifado = precoTarifado;
    }

    @Override
    public String toString() {
        return "Produto(" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", categoria=" + categoria +
            ", precoBase=" + precoBase +
            ", precoTarifado=" + precoTarifado +
            ')';
    }
}
