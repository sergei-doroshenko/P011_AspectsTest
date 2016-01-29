package org.sergei.test.business.service.impl;

import org.junit.Test;
import org.sergei.business.session.dto.Group;
import org.sergei.business.session.impl.SessionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sergei on 12/22/15.
 */
public class SessionServiceTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void testSessionService() {
        SessionServiceImpl service = new SessionServiceImpl();
        service.createSession("001", Group.USER);
        log.warn("User id: {}", service.getCurrentSession().getUserId());
//        log.debug("Session id: {}", service.getCurrentSession().getRefId());
    }
}
