package org.sergei.aspect.after.annotated.impl;

import org.sergei.aspect.after.annotated.ExceptionHandler;

/**
 * Created by sergei on 2/3/16.
 */
public class ExceptionHandlerImpl implements ExceptionHandler {

    @Override
    public void handle(Throwable e) {
        System.out.println("Handler process: " + e.getMessage());
    }
}
