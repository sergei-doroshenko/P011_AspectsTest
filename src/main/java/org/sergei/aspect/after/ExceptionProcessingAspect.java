/**
 * Created by m91snik on 22.02.15.
 */
package org.sergei.aspect.after;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.sergei.business.exception.ImportantException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example of exception processing aspect which applied after exception throwing and can be used to send notification
 */
@Aspect
public class ExceptionProcessingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionProcessingAspect.class);


    @AfterThrowing(pointcut = "org.sergei.aspect.pointcut.ServicePointcut.businessMethodPointcut()",
            throwing = "e")
    public void processImportantException(JoinPoint joinPoint, Throwable e) throws Throwable {
        if (!(e instanceof ImportantException)) {
            throw e;
        }
        LOGGER.error("Exception occurred in " + joinPoint.getSignature() +
                " with args " + new Gson().toJson(joinPoint.getArgs()));
        //NOTE: sending notification
        LOGGER.error("Sending exception notification..." + e.getMessage());
        throw e;
    }

}
