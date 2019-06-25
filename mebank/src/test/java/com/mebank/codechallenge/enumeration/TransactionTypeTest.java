package com.mebank.codechallenge.enumeration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit test for Transaction Type Enumeration
 */
@RunWith(JUnit4.class)
public class TransactionTypeTest {

    @Test
    public void testPaymentType() {
        Assert.assertEquals(TransactionType.PAYMENT.getCode(), "PAYMENT");
    }

    @Test
    public void testReversalType() {
        Assert.assertEquals(TransactionType.REVERSAL.getCode(), "REVERSAL");
    }
}
