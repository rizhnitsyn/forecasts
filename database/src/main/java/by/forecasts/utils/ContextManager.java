package by.forecasts.utils;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class ContextManager {

    private ContextManager() {}

    private static AnnotationConfigApplicationContext APPLICATION_CONTEXT;

    public static void configContext(Class<?> config) {
        if (APPLICATION_CONTEXT == null) {
            APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(config);
        }

    }
}
