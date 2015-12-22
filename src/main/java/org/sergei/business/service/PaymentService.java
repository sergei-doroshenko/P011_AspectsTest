/**
 * Created by nikolay.garbuzov on 23.02.15.
 */
package org.sergei.business.service;


import java.util.List;

public interface PaymentService {

    void doCredit(List<Long> amounts);
}
