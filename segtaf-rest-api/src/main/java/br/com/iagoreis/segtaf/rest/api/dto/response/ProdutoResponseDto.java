package br.com.iagoreis.segtaf.rest.api.dto.response;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDto {

    private Long id;

    private String nome;

    private Categoria categoria;

    @JsonProperty("preco_base")
    private BigDecimal precoBase;

    @JsonProperty("preco_tarifado")
    private BigDecimal precoTarifado;

}
