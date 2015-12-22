
package org.sergei.business.session.dto;

import org.sergei.business.session.Group;

public class Session {
    private final String userId;
    private final Group group;

    public Session(String userId, Group group) {
        this.group = group;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public Group getGroup() {
        return group;
    }
}
