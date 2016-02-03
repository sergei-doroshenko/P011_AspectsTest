package org.sergei.business.dao.impl;

import org.sergei.aspect.after.annotated.HandleException;
import org.sergei.aspect.after.annotated.impl.ExceptionHandlerImpl;
import org.sergei.business.dao.PricesDao;
import org.springframework.stereotype.Repository;

/**
 * Created by sergei on 2/3/16.
 */
@Repository
public class PricesDaoImpl implements PricesDao {

    @Override
    // It doesn't works without arguments, may be needs default arguments in annotation???
//    @HandleException
    @HandleException(exceptions = RuntimeException.class, handlers = ExceptionHandlerImpl.class)
    public String getPrice(String name) {
        if (name == "Comp") {
            return "150";
        } else {
            throw new RuntimeException("Wrong item name");
        }
    }
}
