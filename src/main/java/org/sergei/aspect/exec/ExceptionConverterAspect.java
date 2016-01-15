package org.sergei.aspect.exec;

import org.aspectj.lang.ProceedingJoinPoint;
import org.sergei.business.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sergei on 1/15/16.
 */
public class ExceptionConverterAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionConverterAspect.class);

    /**
     * Intercepts business methods.
     *
     * @param pjp the join point
     * @return an object
     * @throws BusinessException a business exception
     */
    public Object interceptBusinessMethod(ProceedingJoinPoint pjp) throws BusinessException {
        try {
            return pjp.proceed();
        } catch (Throwable ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new BusinessException(ex.getMessage(), ex);
        }
    }
}
