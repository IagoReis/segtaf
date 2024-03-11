package br.com.iagoreis.segtaf.rest.api.controller;

import br.com.iagoreis.segtaf.application.business.usecase.AlterarProdutoUseCase;
import br.com.iagoreis.segtaf.application.business.usecase.BuscarProdutoPorIdUseCase;
import br.com.iagoreis.segtaf.application.business.usecase.CadastrarProdutoUseCase;
import br.com.iagoreis.segtaf.application.business.usecase.ExcluirProdutoPorIdUseCase;
import br.com.iagoreis.segtaf.rest.api.dto.request.ProdutoRequestDto;
import br.com.iagoreis.segtaf.rest.api.dto.response.ProdutoResponseDto;
import br.com.iagoreis.segtaf.rest.api.mapper.dto.ProdutoRequestDtoMapper;
import br.com.iagoreis.segtaf.rest.api.mapper.dto.ProdutoResponseDtoMapper;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    private final CadastrarProdutoUseCase cadastrarProdutoUseCase;

    private final AlterarProdutoUseCase alterarProdutoUseCase;

    private final BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase;

    private final ExcluirProdutoPorIdUseCase excluirProdutoPorIdUseCase;

    private final ProdutoRequestDtoMapper produtoRequestDtoMapper;

    private final ProdutoResponseDtoMapper produtoResponseDtoMapper;

    public ProdutoController(
        final CadastrarProdutoUseCase cadastrarProdutoUseCase,
        final AlterarProdutoUseCase alterarProdutoUseCase,
        final BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase,
        final ExcluirProdutoPorIdUseCase excluirProdutoPorIdUseCase,
        final ProdutoRequestDtoMapper produtoRequestDtoMapper,
        final ProdutoResponseDtoMapper produtoResponseDtoMapper
    ) {
        this.cadastrarProdutoUseCase = cadastrarProdutoUseCase;
        this.alterarProdutoUseCase = alterarProdutoUseCase;
        this.buscarProdutoPorIdUseCase = buscarProdutoPorIdUseCase;
        this.excluirProdutoPorIdUseCase = excluirProdutoPorIdUseCase;
        this.produtoRequestDtoMapper = produtoRequestDtoMapper;
        this.produtoResponseDtoMapper = produtoResponseDtoMapper;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> cadastrar(
        @RequestBody @Valid final ProdutoRequestDto produtoRequestDto
    ) {

        logger.info("method=cadastrar, step=starting, produtoRequestDto={}", produtoRequestDto);

        final var produtoRequest = produtoRequestDtoMapper.mapFrom(produtoRequestDto);

        final var produtoResponse = cadastrarProdutoUseCase.execute(produtoRequest);

        final var produtoResponseDto = produtoResponseDtoMapper.mapFrom(produtoResponse);

        logger.info("method=cadastrar, step=finished-success, produtoResponseDto={}", produtoResponseDto);

        return ResponseEntity.ok(produtoResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> alterar(
        @PathVariable final Long id,
        @RequestBody @Valid final ProdutoRequestDto produtoRequestDto
    ) {

        logger.info("method=alterar, step=starting, id={}, produtoRequestDto={}", id, produtoRequestDto);

        final var produtoRequest = produtoRequestDtoMapper.mapFrom(produtoRequestDto);

        final var produtoResponse = alterarProdutoUseCase.execute(id, produtoRequest);

        final var produtoResponseDto = produtoResponseDtoMapper.mapFrom(produtoResponse);

        logger.info("method=alterar, step=finished-success, id={}, produtoResponseDto={}", id, produtoResponseDto);

        return ResponseEntity.ok(produtoResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> buscarPorId(@PathVariable final Long id) {

        logger.info("method=buscarPorId, step=starting, id={}", id);

        final var produtoResponse = buscarProdutoPorIdUseCase.execute(id);

        final var produtoResponseDto = produtoResponseDtoMapper.mapFrom(produtoResponse);

        logger.info("method=buscarPorId, step=finished-success, id={}, produtoResponseDto={}", id, produtoResponseDto);

        return ResponseEntity.ok(produtoResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable final Long id) {

        logger.info("method=excluirPorId, step=starting, id={}", id);

        excluirProdutoPorIdUseCase.execute(id);

        logger.info("method=excluirPorId, step=finished-success, id={}", id);

        return ResponseEntity.noContent().build();
    }

}
