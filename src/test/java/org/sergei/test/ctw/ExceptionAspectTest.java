package org.sergei.test.ctw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sergei.facade.ClientServiceFacade;
import org.sergei.facade.SupplierServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

/**
 * to run test, be sure that all necessary profiles have been set.
 *
 * Created by sergei on 1/15/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class ExceptionAspectTest {

    @Autowired
    private ClientServiceFacade clientServiceFacade;

    @Autowired
    private SupplierServiceFacade supplierServiceFacade;

    @Test
    public void testAspect() {
        System.out.println("Hello");
        Random random = new Random();
        if (random.nextBoolean()) {
            clientServiceFacade.serve();
        } else {
            supplierServiceFacade.serve();
        }


    }
}
