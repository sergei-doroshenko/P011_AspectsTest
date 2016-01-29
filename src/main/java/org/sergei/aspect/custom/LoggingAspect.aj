package org.sergei.aspect.custom;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sergei on 12/21/15.
 */
public aspect LoggingAspect {
    private Logger log = LoggerFactory.getLogger("Domain objects logger");

    pointcut listOperation() : call(* List.*(..));

    pointcut anyUtilityCall() : call(* java.util..*(..));

    pointcut anyCall() : call(* *.*(..));

    pointcut anyDomainCall(Object bean) : call(* org.sergei.domain..*(..)) && this(bean);

    @SuppressAjWarnings // may not match if there are no implementers of the interface...
    before(Object bean) : anyDomainCall(bean) {
        Class clazz = thisJoinPoint.getTarget().getClass();
        String invoker = thisJoinPoint.getThis().getClass().getName();

        Logger logger = LoggerFactory.getLogger(clazz);
        logger.debug("Before call: " + thisJoinPointStaticPart.getSignature().toShortString());
        logger.debug("Invoker name: " + invoker);

    }
}
