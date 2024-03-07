package br.com.iagoreis.segtaf.rest.api.mapper.dto;

import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.rest.api.dto.request.ProdutoRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoRequestDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "precoTarifado", ignore = true)
    Produto mapFrom(ProdutoRequestDto produtoRequestDto);

}
