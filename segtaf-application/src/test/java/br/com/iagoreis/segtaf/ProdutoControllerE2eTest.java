package br.com.iagoreis.segtaf;

import br.com.iagoreis.segtaf.application.business.exception.ProdutoNaoEncontradoException;
import br.com.iagoreis.segtaf.domain.business.enums.Categoria;
import br.com.iagoreis.segtaf.rest.api.controller.ProdutoController;
import br.com.iagoreis.segtaf.rest.api.dto.request.ProdutoRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ProdutoControllerE2eTest {

	@Autowired
	private ProdutoController produtoController;

	@Test
	void testeCrudCompleto() {

		/* *** *** ** */
		/* CADASTRO */
		/* *** *** ** */

		final var nomeCadastro = "Seguro de Vida Individual";
		final var categoriaCadastro = Categoria.VIDA;
		final var precoBaseCadastro = new BigDecimal("100.00");

		final var dtoRequestCadastro = new ProdutoRequestDto(nomeCadastro, categoriaCadastro, precoBaseCadastro);

		final var responseEntityCadastro = produtoController.cadastrar(dtoRequestCadastro);

		assertEquals(HttpStatus.OK.value(), responseEntityCadastro.getStatusCode().value());

		assertNotNull(responseEntityCadastro.getBody());
		assertNotNull(responseEntityCadastro.getBody().getId());
		assertNotNull(responseEntityCadastro.getBody().getPrecoTarifado());

		assertEquals(dtoRequestCadastro.getNome(), responseEntityCadastro.getBody().getNome());
		assertEquals(dtoRequestCadastro.getCategoria(), responseEntityCadastro.getBody().getCategoria());
		assertEquals(dtoRequestCadastro.getPrecoBase(), responseEntityCadastro.getBody().getPrecoBase());
		assertEquals(new BigDecimal("103.20"), responseEntityCadastro.getBody().getPrecoTarifado());

		final var idProduto = responseEntityCadastro.getBody().getId();

		/* *** *** ** */
		/* BUSCA CADASTRO */
		/* *** *** ** */

		final var responseEntityBuscaCadastro = produtoController.buscarPorId(idProduto);

		assertEquals(HttpStatus.OK.value(), responseEntityCadastro.getStatusCode().value());

		assertEquals(responseEntityCadastro.getBody().getId(), responseEntityBuscaCadastro.getBody().getId());
		assertEquals(responseEntityCadastro.getBody().getNome(), responseEntityBuscaCadastro.getBody().getNome());
		assertEquals(responseEntityCadastro.getBody().getPrecoBase(), responseEntityBuscaCadastro.getBody().getPrecoBase());
		assertEquals(responseEntityCadastro.getBody().getCategoria(), responseEntityBuscaCadastro.getBody().getCategoria());
		assertEquals(responseEntityCadastro.getBody().getPrecoTarifado(), responseEntityBuscaCadastro.getBody().getPrecoTarifado());

		/* *** *** ** */
		/* ALTERACAO */
		/* *** *** ** */

		final var nomeAlteracao = "Seguro de Auto Individual";
		final var categoriaAlteracao = Categoria.AUTO;
		final var precoBaseAlteracao = new BigDecimal("50.00");

		final var dtoRequestAlteracao = new ProdutoRequestDto(nomeAlteracao, categoriaAlteracao, precoBaseAlteracao);

		final var responseEntityAlteracao = produtoController.alterar(idProduto, dtoRequestAlteracao);

		assertEquals(HttpStatus.OK.value(), responseEntityAlteracao.getStatusCode().value());

		assertNotNull(responseEntityAlteracao.getBody());

		assertEquals(idProduto, responseEntityAlteracao.getBody().getId());
		assertEquals(dtoRequestAlteracao.getNome(), responseEntityAlteracao.getBody().getNome());
		assertEquals(dtoRequestAlteracao.getCategoria(), responseEntityAlteracao.getBody().getCategoria());
		assertEquals(dtoRequestAlteracao.getPrecoBase(), responseEntityAlteracao.getBody().getPrecoBase());
		assertEquals(new BigDecimal("55.25"), responseEntityAlteracao.getBody().getPrecoTarifado());

		/* *** *** ** */
		/* BUSCA CADASTRO */
		/* *** *** ** */

		final var responseEntityBuscaAlteracao = produtoController.buscarPorId(idProduto);

		assertEquals(HttpStatus.OK.value(), responseEntityCadastro.getStatusCode().value());

		assertEquals(responseEntityAlteracao.getBody().getId(), responseEntityBuscaAlteracao.getBody().getId());
		assertEquals(responseEntityAlteracao.getBody().getNome(), responseEntityBuscaAlteracao.getBody().getNome());
		assertEquals(responseEntityAlteracao.getBody().getPrecoBase(), responseEntityBuscaAlteracao.getBody().getPrecoBase());
		assertEquals(responseEntityAlteracao.getBody().getCategoria(), responseEntityBuscaAlteracao.getBody().getCategoria());
		assertEquals(responseEntityAlteracao.getBody().getPrecoTarifado(), responseEntityBuscaAlteracao.getBody().getPrecoTarifado());

		/* *** *** ** */
		/* EXCLUSAO */
		/* *** *** ** */

		final var responseEntityExclusao = produtoController.excluirPorId(idProduto);

		assertEquals(HttpStatus.NO_CONTENT.value(), responseEntityExclusao.getStatusCode().value());

		assertNull(responseEntityExclusao.getBody());

		/* *** *** ** */
		/* BUSCA COM FALHA DEVIDO EXCLUSAO */
		/* *** *** ** */

		assertThrows(ProdutoNaoEncontradoException.class, () -> {
			produtoController.buscarPorId(idProduto);
		});
	}

}
