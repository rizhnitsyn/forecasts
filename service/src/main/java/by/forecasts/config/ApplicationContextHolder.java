package by.forecasts.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextHolder {

    private static final ApplicationContext APPLICATION_CONTEXT;

    static {
        APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(ServiceConfig.class);
        System.out.println(APPLICATION_CONTEXT);
        System.out.println("init");
    }

    public static <T> T getBean(Class<T> beanClass) {
        return APPLICATION_CONTEXT.getBean(beanClass);
    }
}
