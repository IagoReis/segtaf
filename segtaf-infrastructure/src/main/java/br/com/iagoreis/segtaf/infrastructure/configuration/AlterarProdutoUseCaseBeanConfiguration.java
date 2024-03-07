package br.com.iagoreis.segtaf.infrastructure.configuration;

import br.com.iagoreis.segtaf.application.business.gateway.AlterarProduto;
import br.com.iagoreis.segtaf.application.business.usecase.AlterarProdutoUseCase;
import br.com.iagoreis.segtaf.application.business.usecase.impl.AlterarProdutoUseCaseImpl;
import br.com.iagoreis.segtaf.domain.business.usecase.CalcularPrecoTarifadoCategoriaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlterarProdutoUseCaseBeanConfiguration {

    @Bean
    public AlterarProdutoUseCase getAlterarProdutoUseCase(
        final CalcularPrecoTarifadoCategoriaUseCase calcularPrecoTarifadoCategoriaUseCase,
        final AlterarProduto alterarProduto
    ) {
        return new AlterarProdutoUseCaseImpl(calcularPrecoTarifadoCategoriaUseCase, alterarProduto);
    }

}
