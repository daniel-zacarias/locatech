package br.com.fiap.localtech.locatech.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorConfig {
    @Bean
    public LocalValidatorFactoryBean validatorFactory(ApplicationContext context) {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setApplicationContext(context);
        return factory;
    }

}
