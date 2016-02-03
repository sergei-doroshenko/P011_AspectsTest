package org.sergei.test.business.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sergei.business.dao.PricesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sergei on 2/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class PricesDaoImplTest {

    @Autowired
    private PricesDao pricesDao;

    @Test
    public void testGetPrice() {
        String result = pricesDao.getPrice("Comp");
        System.out.println(result);
    }

    @Test(expected = RuntimeException.class)
    public void testWrongGetPrice() {
        String result = pricesDao.getPrice("test");
        System.out.println(result);
    }
}
