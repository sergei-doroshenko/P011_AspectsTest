/**
 * Created by nikolay.garbuzov on 23.02.15.
 */
package org.sergei.business.session;

import org.sergei.business.session.dto.Session;

public interface SessionService {

    Session getCurrentSession();

    Session createSession(String userId, Group group);
}
