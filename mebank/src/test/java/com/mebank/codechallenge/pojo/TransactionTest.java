package com.mebank.codechallenge.pojo;

import com.mebank.codechallenge.enumeration.TransactionType;
import com.mebank.codechallenge.utils.ServiceUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

/**
 * Unit test for Transaction
 */
@RunWith(JUnit4.class)
public class TransactionTest {

    private Transaction transaction;

    @Before
    public void setUp() {
        transaction = Transaction.builder().transactionId("TX10001").fromAccountId("ACC334455").toAccountId("ACC778899")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 12:47:55")).
                        amount(new BigDecimal("25.00")).transactionType(TransactionType.PAYMENT).relatedTransaction("TX10001").build();
    }

    @Test
    public void testTransactionGetters() {
        Assert.assertEquals(transaction.getTransactionId(), "TX10001");
        Assert.assertEquals(transaction.getFromAccountId(), "ACC334455");
        Assert.assertEquals(transaction.getToAccountId(), "ACC778899");
        Assert.assertEquals(transaction.getCreatedAt(), ServiceUtil.convertStringToLocalDateTime("20/10/2018 12:47:55"));
        Assert.assertEquals(transaction.getAmount(), new BigDecimal("25.00"));
        Assert.assertEquals(transaction.getTransactionType(), TransactionType.PAYMENT);
        Assert.assertEquals(transaction.getRelatedTransaction(), "TX10001");
    }
}
