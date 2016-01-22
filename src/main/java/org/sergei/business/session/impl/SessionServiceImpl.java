/**
 * Created by nikolay.garbuzov on 23.02.15.
 */
package org.sergei.business.session.impl;

import org.sergei.business.session.Group;
import org.sergei.business.session.SessionService;
import org.sergei.business.session.dto.Session;
import org.springframework.stereotype.Service;


@Service
public class SessionServiceImpl implements SessionService {

    private final ThreadLocal<Session> SESSION_HOLDER = new ThreadLocal<>();

    @Override
    public Session getCurrentSession() {
        return SESSION_HOLDER.get();
    }

    @Override
    public Session createSession(String userId, Group group) {
        Session session = new Session(userId, group);
        SESSION_HOLDER.set(session);
        return session;
    }
}
