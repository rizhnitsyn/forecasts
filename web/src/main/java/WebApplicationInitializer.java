import by.forecasts.config.AspectConfig;
import by.forecasts.config.CacheConfig;
import by.forecasts.config.ServiceConfig;
import config.InternationalizationConfig;
import config.SecurityConfig;
import config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {ServiceConfig.class, AspectConfig.class, SecurityConfig.class,
                InternationalizationConfig.class, CacheConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class};
    }


    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
