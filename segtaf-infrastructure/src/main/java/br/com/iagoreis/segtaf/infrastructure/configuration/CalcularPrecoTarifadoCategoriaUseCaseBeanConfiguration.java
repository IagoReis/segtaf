package br.com.iagoreis.segtaf.infrastructure.configuration;

import br.com.iagoreis.segtaf.domain.business.usecase.CalcularPrecoTarifadoCategoriaUseCase;
import br.com.iagoreis.segtaf.domain.business.usecase.impl.CalcularPrecoTarifadoCategoriaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalcularPrecoTarifadoCategoriaUseCaseBeanConfiguration {

    @Bean
    public CalcularPrecoTarifadoCategoriaUseCase getCalcularPrecoTarifadoCategoriaUseCase() {
        return new CalcularPrecoTarifadoCategoriaUseCaseImpl();
    }

}
