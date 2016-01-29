package org.sergei.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclarePrecedence;

@Aspect
@DeclarePrecedence("org.sergei.aspect.before.SecurityProtectionAspect,org.sergei.aspect.around.LoggingAspectSpring")
public class PrecedenceAspect {
}
