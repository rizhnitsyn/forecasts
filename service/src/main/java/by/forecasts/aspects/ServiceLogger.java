package by.forecasts.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class ServiceLogger {

    private static final Logger LOG = Logger.getLogger(ServiceLogger.class);

    @Pointcut("execution(* by.forecasts.service.implementation.*.*(..))")
    public void serviceMethod() {}

    @Pointcut("@annotation(by.forecasts.aspects.Loggable)")
    public void loggableMethod() {}


    @Around("serviceMethod() && loggableMethod()")
    public Object logWebServiceCall(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();

        LOG.info("Call method " + methodName + " with args " + Arrays.toString(methodArgs));
        Object result = thisJoinPoint.proceed();
        LOG.info("Method " + methodName + " returns " + result);

        return result;
    }

}
