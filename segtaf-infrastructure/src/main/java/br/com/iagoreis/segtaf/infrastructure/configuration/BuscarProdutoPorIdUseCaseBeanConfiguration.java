package br.com.iagoreis.segtaf.infrastructure.configuration;

import br.com.iagoreis.segtaf.application.business.gateway.BuscarProdutoPorId;
import br.com.iagoreis.segtaf.application.business.usecase.BuscarProdutoPorIdUseCase;
import br.com.iagoreis.segtaf.application.business.usecase.impl.BuscarProdutoPorIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarProdutoPorIdUseCaseBeanConfiguration {

    @Bean
    public BuscarProdutoPorIdUseCase getBuscarProdutoPorIdUseCase(final BuscarProdutoPorId buscarProdutoPorId) {
        return new BuscarProdutoPorIdUseCaseImpl(buscarProdutoPorId);
    }

}
