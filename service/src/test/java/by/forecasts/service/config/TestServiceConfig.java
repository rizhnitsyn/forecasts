package by.forecasts.service.config;

import by.forecasts.config.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseConfig.class)
@ComponentScan(basePackages = {"by.forecasts.service"})
public class TestServiceConfig {
}
