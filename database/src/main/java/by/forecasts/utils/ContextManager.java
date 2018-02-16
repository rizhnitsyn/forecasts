package by.forecasts.utils;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class ContextManager {

    private ContextManager() {}

    private static AnnotationConfigApplicationContext applicationContext;

    public static void initContext(Class<?> config) {
        if (applicationContext == null) {
            applicationContext = new AnnotationConfigApplicationContext(config);
        }
    }

    public Object getBean(Class<?> bean) {
        return applicationContext.getBean(bean);
    }
}
