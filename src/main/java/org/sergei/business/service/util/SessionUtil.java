
package org.sergei.business.service.util;

import org.sergei.business.session.Group;
import org.sergei.business.session.SessionService;

public class SessionUtil {

    private SessionService sessionService;

    public boolean validateSession(Group group) {
        return group == sessionService.getCurrentSession().getGroup();
    }
}
