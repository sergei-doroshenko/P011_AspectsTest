package org.sergei.business.service.impl;

import org.sergei.business.service.BankService;
import org.sergei.contract.UnsafeOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by nikolay.garbuzov on 25.02.15.
 */
@Service
public class BankServiceImpl implements BankService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    @UnsafeOperation
    public long transferMoney(long amount) {
        log.debug("Doing money transfer for {}", amount);
        return amount;
    }
}
