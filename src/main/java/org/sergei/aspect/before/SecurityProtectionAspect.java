/**
 * Created by m91snik on 22.02.15.
 */
package org.sergei.aspect.before;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.sergei.business.session.Group;
import org.sergei.business.session.SessionService;
import org.sergei.business.session.dto.Session;
import org.sergei.contract.SessionRequired;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@Aspect
public class SecurityProtectionAspect {

    @Autowired
    private SessionService sessionService;

    //NOTE: it provides more flexibility than Spring @Secured contract because you can use any pointcut here
    @Before("org.sergei.aspect.pointcut.ServicePointcut.securedBusinessMethodPointcut() && @annotation(sessionRequired)")
    public void checkUserPermission(SessionRequired sessionRequired) throws Throwable {
        Session currentSession = sessionService.getCurrentSession();

        if (currentSession == null) {
            throw new IllegalStateException("Session is null");
        }

        if (!checkPermissions(sessionRequired, currentSession)) {
            System.err.println("Security rules violated, group is " + currentSession.getGroup());
            throw new IllegalStateException(currentSession.getGroup() + " not allowed");
        }
    }

    private boolean checkPermissions(SessionRequired sessionRequired, Session currentSession) {
        Group currentGroup = currentSession.getGroup();
        return Arrays.stream(sessionRequired.group())
                .filter(group -> group == currentGroup)
                .findFirst().isPresent();
    }

}
