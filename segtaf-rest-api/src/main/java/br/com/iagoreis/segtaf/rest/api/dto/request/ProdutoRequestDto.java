package br.com.iagoreis.segtaf.rest.api.dto.request;

import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProdutoRequestDto {

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @NotNull(message = "O campo categoria é obrigatório")
    private Categoria categoria;

    @NotNull(message = "O campo preco_base é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor do preco_base deve ser maior que 0.00")
    @JsonProperty("preco_base")
    private BigDecimal precoBase;
}
