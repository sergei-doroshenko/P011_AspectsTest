/**
 * Created by m91snik on 22.02.15.
 */
package org.sergei.aspect.pointcut;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Collection of pointcuts
 */
public class ServicePointcut {

    @Pointcut("execution(* org.sergei.business.service..*(..))")
    public void businessMethodPointcut(){
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
