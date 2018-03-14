package by.forecasts.config;

import by.forecasts.aspects.ServiceLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Bean
    public ServiceLogger audienceAspect() {
        return new ServiceLogger();
    }
}
