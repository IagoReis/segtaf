package br.com.iagoreis.segtaf.database.gateway;

import br.com.iagoreis.segtaf.application.business.exception.ProdutoNaoEncontradoException;
import br.com.iagoreis.segtaf.database.ProdutoRepository;
import br.com.iagoreis.segtaf.database.model.ProdutoEntity;
import br.com.iagoreis.segtaf.domain.business.entity.Produto;
import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExcluirProdutoPorIdImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ExcluirProdutoPorIdImpl excluirProdutoPorId;

    private final Long id = 1l;
    private final String nome = "Seguro de Vida III";
    private final Categoria categoria = Categoria.VIDA;
    private final BigDecimal precoBase = BigDecimal.valueOf(100.00);
    private final BigDecimal precoTarifado = BigDecimal.valueOf(110.00);

    @Test
    void testeExclusaoProduto() {

        final var produto = new Produto(id, nome, categoria, precoBase, precoTarifado);

        when(produtoRepository.findById(eq(produto.getId()))).thenReturn(Optional.of(mapFrom(produto)));

        assertDoesNotThrow(() -> {
            excluirProdutoPorId.excluir(produto.getId());
        });
    }

    @Test
    void garantirQueEstoureUmaExcecaoCasoOProdutoNaoExista() {

        when(produtoRepository.findById(eq(id))).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            excluirProdutoPorId.excluir(id);
        });
    }

    private ProdutoEntity mapFrom(final Produto produto) {
        return new ProdutoEntity(produto.getId(), produto.getNome(), produto.getCategoria(), produto.getPrecoBase(), produto.getPrecoTarifado());
    }

}
