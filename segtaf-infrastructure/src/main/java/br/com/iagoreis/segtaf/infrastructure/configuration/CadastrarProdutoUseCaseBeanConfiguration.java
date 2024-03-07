package br.com.iagoreis.segtaf.infrastructure.configuration;

import br.com.iagoreis.segtaf.application.business.gateway.CadastrarProduto;
import br.com.iagoreis.segtaf.application.business.usecase.CadastrarProdutoUseCase;
import br.com.iagoreis.segtaf.application.business.usecase.impl.CadastrarProdutoUseCaseImpl;
import br.com.iagoreis.segtaf.domain.business.usecase.CalcularPrecoTarifadoCategoriaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastrarProdutoUseCaseBeanConfiguration {

    @Bean
    public CadastrarProdutoUseCase getCadastrarProdutoUseCase(
        final CalcularPrecoTarifadoCategoriaUseCase calcularPrecoTarifadoCategoriaUseCase,
        final CadastrarProduto cadastrarProduto
    ) {
        return new CadastrarProdutoUseCaseImpl(calcularPrecoTarifadoCategoriaUseCase, cadastrarProduto);
    }

}
