package eu.mithril.invoiceservice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(eu.mithril.invoiceservice.service.*)")
    public void everyMethodInServicePackage() {
    }

    @Around("everyMethodInServicePackage()")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("Executing {}.{} with args: {}", className, methodName, joinPoint.getArgs());

        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;

            log.info("Completed {}.{} in {}ms with result:{}", className, methodName, duration, result);

            return result;
        } catch (Exception e) {
            log.error("Error in {}.{}", className, methodName);
            throw new RuntimeException(e);
        }
    }
}
