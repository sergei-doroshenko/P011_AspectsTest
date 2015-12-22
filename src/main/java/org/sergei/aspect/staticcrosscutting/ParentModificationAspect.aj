
package org.sergei.aspect.staticcrosscutting;

/**
 * Adds parent (interface) for the specific classes
 */
public aspect ParentModificationAspect {

    declare parents : org.sergei.business.session.dto..* implements org.sergei.contract.HasRefId;
}
