package org.sergei.test.business.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sergei.business.exception.ImportantException;
import org.sergei.business.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class PricesServiceImplTest {

    @Autowired
    private PriceService priceService;

    @Test
    public void testValidatePrices() throws Exception {
        priceService.validatePrices(Arrays.asList(100));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullValidatePrices() throws Exception {
        priceService.validatePrices(null);
    }

    @Test(expected = ImportantException.class)
    public void testOverflowValidatePrices() throws Exception {
        priceService.validatePrices(Arrays.asList(1000, 2000));
    }

    @Test
    public void testCalculateAvgPrice() throws Exception {
        double avg = priceService.calculateAvgPrice(Arrays.asList(100, 200));
        System.out.println("Avg price is " + avg);
        Assert.assertEquals(150.0, avg, 0);

    }
}