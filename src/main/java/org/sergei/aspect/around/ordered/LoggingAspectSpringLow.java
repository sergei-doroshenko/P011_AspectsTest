package org.sergei.aspect.around.ordered;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

/**
 * Created by sergei on 1/28/16.
 *
 * In general we call here org.springframework.aop.framework.JdkDynamicAopProxy::invoke
 *
 *
 */
@Aspect
public class LoggingAspectSpringLow implements Ordered {
    private static final int ORDER = 200;
    private static final Gson GSON = new Gson();

    @Around("org.sergei.aspect.pointcut.ServicePointcut.businessMethodPointcutOrdered()")
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
        System.out.println("LoggingAspectSpringLow: " + message + "; ORDER=" + ORDER);
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
