package org.sergei.aspect.staticcrosscutting;

import java.io.IOException;

public aspect ExceptionSofteningAspect {
    declare soft :IOException: execution(* org.sergei.business.service.util.BytesUtil.*(..));
}
