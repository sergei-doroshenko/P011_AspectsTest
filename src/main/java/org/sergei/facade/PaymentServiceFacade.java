package org.sergei.facade;

import org.sergei.business.service.PaymentService;
import org.sergei.business.session.Group;
import org.sergei.business.session.dto.Session;
import org.sergei.contract.SessionRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sergei on 12/22/15.
 */
@Service
public class PaymentServiceFacade {
    @Autowired
    private PaymentService paymentService;

    @SessionRequired(group = Group.USER)
    public void creditPayment(Session session, List<Long> amount) {
        paymentService.doCredit(amount);
    }
}
