package org.sergei.aspect.flow;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sergei.contract.HasRefId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sergei on 12/22/15.
 */
@Aspect
public class PaymentFlowLoggingAspect {
    private Logger log = LoggerFactory.getLogger(getClass());
    private static final Gson GSON = new Gson();

    public PaymentFlowLoggingAspect() {
        log.debug("FlowLoggingAspect init");
    }

    @Pointcut("execution(* org.sergei.facade.PaymentServiceFacade.*(org.sergei.contract.HasRefId+,..)) " +
            "&& args(hasRefId,..)")
    public void paymentServiceFacadeUsage(HasRefId hasRefId) {
    }

    @Pointcut("execution(public * org.sergei.business.service..*(..))")
    public void paymentServiceFacadeCalls() {
    }

    @Around("cflow(paymentServiceFacadeUsage(hasRefId)) && paymentServiceFacadeCalls()")
    public Object logMethodExecution(HasRefId hasRefId, ProceedingJoinPoint joinPoint) throws Throwable {
        String refId = hasRefId.getRefId();

        String methodContext = "Session = " + refId + ". Method " + joinPoint.toShortString();
        System.out.println(methodContext + " with args " + "" + GSON.toJson(joinPoint.getArgs()));
//        log.debug("{} with args {}", methodContext, GSON.toJson(joinPoint.getArgs()));
        Object result;
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
//            log.debug("{} return {}", methodContext, GSON.toJson(result));
            System.out.println(methodContext + " returned " + GSON.toJson(result));
        } catch (Throwable t) {
            System.out.println(methodContext + " throw " + t.getMessage());
//            log.debug("{} throw {}", methodContext, t.getMessage());
            throw t;
        }
        return result;
    }
}
