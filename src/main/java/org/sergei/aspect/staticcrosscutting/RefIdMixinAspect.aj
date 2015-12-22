package org.sergei.aspect.staticcrosscutting;

import org.sergei.contract.HasRefId;

import java.util.UUID;

/**
 * Field and method introduction example. It will work only with ajc compilation
 */
public aspect RefIdMixinAspect {
    private String HasRefId.refId;

    public final String HasRefId.getRefId() {
        return refId;
    }

    after (HasRefId hasRefId): execution(HasRefId+.new(..)) && this(hasRefId){
        hasRefId.refId = UUID.randomUUID().toString();
    }
}
