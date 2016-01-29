package org.sergei.aspect.staticcrosscutting;

/**
 * Adds parent (interface) for the specific classes.
 *
 * All classes from package org.sergei.business.session.dto after compilation will implement
 * interface org.sergei.contract.HasRefId.
 */
public aspect ParentModificationAspect {

    declare parents : org.sergei.business.session.dto..* implements org.sergei.contract.HasRefId;
}
