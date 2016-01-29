package org.sergei.aspect.staticcrosscutting;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareWarning;

/**
 * Provides compile check rules
 */
@Aspect
public class CompileValidationAspect {

    @DeclareWarning("call(* org.sergei.business.service.BankService.*(..))")
    private static final String UNSAFE_USAGE_ERROR = "Usage of unsafe method!";

    //NOTE: it's possible to use DeclareError also to forbid compilation in case of rule violation
    @DeclareWarning("get(* System.out) && within(org.sergei.business.service..*)")
    private static final String DEFAULT_LOGGING_USAGE = "Use log4j instead of System.out!!";

    @DeclareWarning("call(* org.sergei.business.session..*(..)) " +
            "&& within(org.sergei.business.service..*)")
    private static final String SERVICE_BOUNDARY_BROKEN = "Service tries to break it's security boundary";


}
