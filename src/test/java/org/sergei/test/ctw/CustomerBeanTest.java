package org.sergei.test.ctw;

import org.junit.Test;
import org.sergei.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by sergei on 12/21/15.
 */
public class CustomerBeanTest {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void addressChangeNotifications() {
        Customer testCustomer = new Customer();
        testCustomer.setAddress("oldAddress");
        final AtomicInteger counter = new AtomicInteger();

        testCustomer.addPropertyChangeListener(
                evt -> {
                    assertEquals("address", evt.getPropertyName());
                    assertEquals("oldAddress", evt.getOldValue());
                    assertEquals("newAddress", evt.getNewValue());
                    counter.incrementAndGet();
                    log.debug("oldAddress: {}, newAddress: {}", evt.getOldValue(), evt.getNewValue());
                });

        testCustomer.setAddress("newAddress");
        assertEquals(1, counter.get());
        log.debug("counter: " + counter.get());
    }
}
