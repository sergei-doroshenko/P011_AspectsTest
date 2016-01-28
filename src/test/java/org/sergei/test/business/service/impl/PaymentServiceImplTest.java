package org.sergei.test.business.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sergei.business.service.PaymentService;
import org.sergei.business.session.SessionService;
import org.sergei.business.session.dto.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * Created by sergei on 1/28/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")

public class PaymentServiceImplTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private SessionService sessionService;

    @Test
    public void testDoCredit() throws Exception {
        sessionService.createSession("1", Group.USER);
        paymentService.doCredit(Arrays.asList(10L, 20L));
    }

    @Test(expected = IllegalStateException.class)
    public void testDoCreditPermissionRestricted() throws Exception {
        sessionService.createSession("1", Group.ANONYMOUS);
        paymentService.doCredit(Arrays.asList(10L, 20L));
    }
}
