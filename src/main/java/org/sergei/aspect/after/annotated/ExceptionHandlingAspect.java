package org.sergei.aspect.after.annotated;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example of exception handling aspect which applied after exception throwing and can be used to send notification.
 * In this example aspect advise applied to method annotated with @HandleException pointcut.
 *
 */
@Aspect
public class ExceptionHandlingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingAspect.class);


    @AfterThrowing(pointcut = "org.sergei.aspect.pointcut.ServicePointcut.daoMethodPointcut() " +
            "&& @annotation(handleException)",
            throwing = "e")
    public void handleDaoException(JoinPoint joinPoint, HandleException handleException, Throwable e) throws Throwable {
        // Get parameters from annotation (meta information)
        Class<? extends Throwable>[] exceptions = handleException.exceptions();
        Class<? extends ExceptionHandler>[] handlers = handleException.handlers();

        if (!checkException(exceptions, e)) {
            throw e;
        }

        LOGGER.error("Exception occurred in " + joinPoint.getSignature() + " with args " + new Gson().toJson(joinPoint.getArgs()));
        //NOTE: sending notification
        LOGGER.error("Sending exception notification..." + e.getMessage());
//        Stream.of(handlers).forEach(( Class<? extends ExceptionHandler> c) -> c.newInstance().handle(e) );

        for(Class<? extends ExceptionHandler> clazz : handlers) {
            ExceptionHandler handler = clazz.newInstance();
            handler.handle(e);
        }

        throw e;
    }

    // Check that we get correct exception
    private boolean checkException(Class<? extends Throwable>[] exceptions, Throwable e) {

        for (Class<? extends Throwable> clazz : exceptions) {
            if (clazz.isAssignableFrom(e.getClass())) {
                return true;
            }
        }

        return false;
    }

}
