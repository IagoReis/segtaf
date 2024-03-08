package br.com.iagoreis.segtaf.rest.api.controller;

import br.com.iagoreis.segtaf.application.business.usecase.AlterarProdutoUseCase;
import br.com.iagoreis.segtaf.application.business.usecase.BuscarProdutoPorIdUseCase;
import br.com.iagoreis.segtaf.application.business.usecase.CadastrarProdutoUseCase;
import br.com.iagoreis.segtaf.application.business.usecase.ExcluirProdutoPorIdUseCase;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import br.com.iagoreis.segtaf.rest.api.dto.request.ProdutoRequestDto;
import br.com.iagoreis.segtaf.rest.api.dto.response.ProdutoResponseDto;
import br.com.iagoreis.segtaf.rest.api.mapper.dto.ProdutoRequestDtoMapper;
import br.com.iagoreis.segtaf.rest.api.mapper.dto.ProdutoResponseDtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {

    @Mock
    private CadastrarProdutoUseCase cadastrarProdutoUseCase;

    @Mock
    private AlterarProdutoUseCase alterarProdutoUseCase;

    @Mock
    private BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase;

    @Mock
    private ExcluirProdutoPorIdUseCase excluirProdutoPorIdUseCase;

    @Mock
    private ProdutoRequestDtoMapper produtoRequestDtoMapper;

    @Mock
    private ProdutoResponseDtoMapper produtoResponseDtoMapper;

    @InjectMocks
    private ProdutoController produtoController;

    private final Long id = 1l;
    private final String nome = "Seguro de Vida III";
    private final Categoria categoria = Categoria.VIDA;
    private final BigDecimal precoBase = BigDecimal.valueOf(100.00);
    private final BigDecimal precoTarifado = BigDecimal.valueOf(110.00);
    private final ProdutoRequestDto produtoRequestDto = new ProdutoRequestDto(nome, categoria, precoBase);

    final Produto produto = new Produto(id, nome, categoria, precoBase, precoTarifado);

    @Test
    void testeDeCadastroComSucesso() {

        when(produtoRequestDtoMapper.mapFrom(any(ProdutoRequestDto.class))).thenReturn(mapFrom(produtoRequestDto));
        when(cadastrarProdutoUseCase.execute(any(Produto.class))).thenReturn(produto);
        when(produtoResponseDtoMapper.mapFrom(any(Produto.class))).thenReturn(mapFrom(produto));

        final var responseEntity = produtoController.cadastrar(produtoRequestDto);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final var produtoResponsetDto = responseEntity.getBody();

        assertNotNull(produtoResponsetDto);
        assertNotNull(produtoResponsetDto.getId());
        assertNotNull(produtoResponsetDto.getPrecoTarifado());

        assertEquals(produtoRequestDto.getNome(), produtoResponsetDto.getNome());
        assertEquals(produtoRequestDto.getCategoria(), produtoResponsetDto.getCategoria());
        assertEquals(produtoRequestDto.getPrecoBase(), produtoResponsetDto.getPrecoBase());
    }

    @Test
    void testeDeAlteracaoComSucesso() {

        when(produtoRequestDtoMapper.mapFrom(any(ProdutoRequestDto.class))).thenReturn(mapFrom(produtoRequestDto));
        when(alterarProdutoUseCase.execute(eq(id), any(Produto.class))).thenReturn(produto);
        when(produtoResponseDtoMapper.mapFrom(any(Produto.class))).thenReturn(mapFrom(produto));

        final var responseEntity = produtoController.alterar(produto.getId(), produtoRequestDto);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final var produtoResponsetDto = responseEntity.getBody();

        assertNotNull(produtoResponsetDto);
        assertNotNull(produtoResponsetDto.getId());
        assertNotNull(produtoResponsetDto.getPrecoTarifado());

        assertEquals(id, produtoResponsetDto.getId());
        assertEquals(produtoRequestDto.getNome(), produtoResponsetDto.getNome());
        assertEquals(produtoRequestDto.getCategoria(), produtoResponsetDto.getCategoria());
        assertEquals(produtoRequestDto.getPrecoBase(), produtoResponsetDto.getPrecoBase());
    }

    @Test
    void testeDeBuscaPorIdComSucesso() {

        when(buscarProdutoPorIdUseCase.execute(eq(id))).thenReturn(produto);
        when(produtoResponseDtoMapper.mapFrom(any(Produto.class))).thenReturn(mapFrom(produto));

        final var responseEntity = produtoController.buscarPorId(id);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final var produtoResponsetDto = responseEntity.getBody();

        assertNotNull(produtoResponsetDto);

        assertEquals(produto.getId(), produtoResponsetDto.getId());
        assertEquals(produto.getNome(), produtoResponsetDto.getNome());
        assertEquals(produto.getCategoria(), produtoResponsetDto.getCategoria());
        assertEquals(produto.getPrecoBase(), produtoResponsetDto.getPrecoBase());
        assertEquals(produto.getPrecoTarifado(), produtoResponsetDto.getPrecoTarifado());
    }

    @Test
    void testeDeExclusaoPorIdComSucesso() {

        final var responseEntity = produtoController.excluirPorId(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    private Produto mapFrom(final ProdutoRequestDto produtoRequestDto) {
        return new Produto(
            null,
            produtoRequestDto.getNome(),
            produtoRequestDto.getCategoria(),
            produtoRequestDto.getPrecoBase(),
            null
        );
    }

    private ProdutoResponseDto mapFrom(final Produto produto) {
        return new ProdutoResponseDto(
            produto.getId(),
            produto.getNome(),
            produto.getCategoria(),
            produto.getPrecoBase(),
            produto.getPrecoTarifado()
        );
    }

}
