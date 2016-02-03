package org.sergei.aspect.after.annotated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sergei on 2/3/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HandleException {

    Class<? extends Throwable>[] exceptions() default {};
    Class<? extends ExceptionHandler>[] handlers() default {};

}
