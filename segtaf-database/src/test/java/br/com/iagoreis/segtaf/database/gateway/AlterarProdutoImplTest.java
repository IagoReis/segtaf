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
class AlterarProdutoImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoEntityMapper produtoEntityMapper;

    @InjectMocks
    private AlterarProdutoImpl alterarProduto;

    private final Long id = 1l;
    private final String nome = "Seguro de Vida III";
    private final Categoria categoria = Categoria.VIDA;
    private final BigDecimal precoBase = BigDecimal.valueOf(100.00);
    private final BigDecimal precoTarifado = BigDecimal.valueOf(110.00);

    @Test
    void testeAlteracaoProduto() {

        final var produtoRequest = new Produto(id, nome, categoria, precoBase, precoTarifado);

        Mockito.when(produtoEntityMapper.mapFrom(Mockito.any(Produto.class))).thenReturn(mapFrom(produtoRequest));
        Mockito.when(produtoRepository.findById(Mockito.eq(produtoRequest.getId()))).thenReturn(Optional.of(mapFrom(produtoRequest)));
        Mockito.when(produtoRepository.save(Mockito.any(ProdutoEntity.class))).thenReturn(mapFrom(produtoRequest));
        Mockito.when(produtoEntityMapper.mapFrom(Mockito.any(ProdutoEntity.class))).thenReturn(produtoRequest);

        final var produtoResponse = alterarProduto.alterar(produtoRequest.getId(), produtoRequest);

        assertNotNull(produtoResponse);
        assertEquals(produtoRequest.getId(), produtoResponse.getId());
        assertEquals(produtoRequest.getNome(), produtoResponse.getNome());
        assertEquals(produtoRequest.getCategoria(), produtoResponse.getCategoria());
        assertEquals(produtoRequest.getPrecoBase(), produtoResponse.getPrecoBase());
        assertEquals(produtoRequest.getPrecoTarifado(), produtoResponse.getPrecoTarifado());
    }

    @Test
    void garantirQueEstoureUmaExcecaoCasoOProdutoNaoExista() {

        final var produtoRequest = new Produto(id, nome, categoria, precoBase, precoTarifado);

        Mockito.when(produtoRepository.findById(Mockito.eq(produtoRequest.getId()))).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            alterarProduto.alterar(produtoRequest.getId(), produtoRequest);
        });
    }

    private ProdutoEntity mapFrom(final Produto produto) {
        return new ProdutoEntity(produto.getId(), produto.getNome(), produto.getCategoria(), produto.getPrecoBase(), produto.getPrecoTarifado());
    }

}
