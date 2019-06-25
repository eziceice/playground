package com.myob.codeexercise.properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit Test for tax properties
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxPropertiesTest {

    @Autowired
    private TaxProperties taxProperties;

    @Test
    public void testTaxProperties() {
        Assert.assertEquals("12", taxProperties.getMonth());
    }
}
