package org.sergei.aspect.around;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by sergei on 1/28/16.
 */
@Aspect
public class LoggingAspectSpring {
    private static final Gson GSON = new Gson();

      @Around("org.sergei.aspect.pointcut.ServicePointcut.businessMethodCallPointcut()")
//  @Around("org.sergei.aspect.pointcut.ServicePointcut.businessMethodPointcutWithin()")
//    @Around("execution(* org.sergei.business.service..*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();

        log(methodName + " - arguments {" + GSON.toJson(args) + "}");

        Object result;
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable t) {
            log(methodName + " - failed with " + t.getMessage());
            throw t;
        }

        log(methodName + " - result {" + GSON.toJson(result) + "}");

        return result;
    }

    private void log(String message) {
        System.out.println(message);
    }
}
