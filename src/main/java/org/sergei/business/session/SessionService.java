package org.sergei.business.session;

import org.sergei.business.session.dto.Group;
import org.sergei.business.session.dto.Session;

public interface SessionService {

    Session getCurrentSession();

    Session createSession(String userId, Group group);
}
