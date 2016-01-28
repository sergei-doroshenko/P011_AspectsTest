package org.sergei.aspect.pointcut;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Collection of pointcuts
 */
public class ServicePointcut {

    @Pointcut("execution(* org.sergei.business.service..*(..))") // the pointcut expression
    public void businessMethodPointcut(){ // the pointcut signature
    }

    @Pointcut("execution(@org.sergei.contract.SessionRequired * org.sergei.business.service..*(..))")
    public void securedBusinessMethodPointcut(){
    }

    @Pointcut("execution(* org.sergei.business..*(..)) && " +
            "(within(org.sergei.business.service..*) || " +
            "within(org.sergei.business.session.impl..*))")
    public void businessMethodPointcutWithin(){
    }

    @Pointcut("call(* org.sergei.business..*(..)) && " +
            "(within(org.sergei.business.service..*) || " +
            "within(org.sergei.business.session.impl..*))")
    public void businessMethodCallPointcut(){
    }

}
