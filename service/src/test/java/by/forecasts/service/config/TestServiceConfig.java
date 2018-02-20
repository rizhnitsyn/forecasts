package by.forecasts.service.config;

import by.forecasts.config.PersistenceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfig.class)
@ComponentScan(basePackages = {"by.forecasts.service"})
public class TestServiceConfig {
}
