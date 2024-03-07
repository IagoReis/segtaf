package br.com.iagoreis.segtaf.rest.api.mapper.dto;

import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.rest.api.dto.response.ProdutoResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoResponseDtoMapper {

    ProdutoResponseDto mapFrom(Produto produto);

}
