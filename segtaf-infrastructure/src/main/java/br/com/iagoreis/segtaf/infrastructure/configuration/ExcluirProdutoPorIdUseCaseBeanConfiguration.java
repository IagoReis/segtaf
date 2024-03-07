package br.com.iagoreis.segtaf.infrastructure.configuration;

import br.com.iagoreis.segtaf.application.business.gateway.ExcluirProdutoPorId;
import br.com.iagoreis.segtaf.application.business.usecase.ExcluirProdutoPorIdUseCase;
import br.com.iagoreis.segtaf.application.business.usecase.impl.ExcluirProdutoPorIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcluirProdutoPorIdUseCaseBeanConfiguration {

    @Bean
    public ExcluirProdutoPorIdUseCase getExcluirProdutoPorIdUseCase(final ExcluirProdutoPorId excluirProdutoPorId) {
        return new ExcluirProdutoPorIdUseCaseImpl(excluirProdutoPorId);
    }

}
