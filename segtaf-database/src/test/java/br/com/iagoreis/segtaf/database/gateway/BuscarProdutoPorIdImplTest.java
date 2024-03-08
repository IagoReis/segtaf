package br.com.iagoreis.segtaf.database.gateway;

import br.com.iagoreis.segtaf.application.business.exception.ProdutoNaoEncontradoException;
import br.com.iagoreis.segtaf.database.ProdutoRepository;
import br.com.iagoreis.segtaf.database.mapper.ProdutoEntityMapper;
import br.com.iagoreis.segtaf.database.model.ProdutoEntity;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BuscarProdutoPorIdImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoEntityMapper produtoEntityMapper;

    @InjectMocks
    private BuscarProdutoPorIdImpl buscarProdutoPorId;

    private final Long id = 1l;
    private final String nome = "Seguro de Vida III";
    private final Categoria categoria = Categoria.VIDA;
    private final BigDecimal precoBase = BigDecimal.valueOf(100.00);
    private final BigDecimal precoTarifado = BigDecimal.valueOf(110.00);

    @Test
    void testeBuscaProduto() {

        final var produto = new Produto(id, nome, categoria, precoBase, precoTarifado);

        Mockito.when(produtoRepository.findById(Mockito.eq(produto.getId()))).thenReturn(Optional.of(mapFrom(produto)));
        Mockito.when(produtoEntityMapper.mapFrom(Mockito.any(ProdutoEntity.class))).thenReturn(produto);

        final var produtoResponse = buscarProdutoPorId.buscar(produto.getId());

        assertNotNull(produtoResponse);
        assertEquals(produto.getId(), produtoResponse.getId());
        assertEquals(produto.getNome(), produtoResponse.getNome());
        assertEquals(produto.getCategoria(), produtoResponse.getCategoria());
        assertEquals(produto.getPrecoBase(), produtoResponse.getPrecoBase());
        assertEquals(produto.getPrecoTarifado(), produtoResponse.getPrecoTarifado());
    }

    @Test
    void garantirQueEstoureUmaExcecaoCasoOProdutoNaoExista() {

        Mockito.when(produtoRepository.findById(Mockito.eq(id))).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            buscarProdutoPorId.buscar(id);
        });
    }

    private ProdutoEntity mapFrom(final Produto produto) {
        return new ProdutoEntity(produto.getId(), produto.getNome(), produto.getCategoria(), produto.getPrecoBase(), produto.getPrecoTarifado());
    }

}
