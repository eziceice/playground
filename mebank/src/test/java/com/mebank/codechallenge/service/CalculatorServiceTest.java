package com.mebank.codechallenge.service;

import com.mebank.codechallenge.enumeration.TransactionType;
import com.mebank.codechallenge.pojo.Transaction;
import com.mebank.codechallenge.utils.ServiceUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

/**
 * Unit Test for Calculator service
 */
@RunWith(SpringRunner.class)
public class CalculatorServiceTest {

    @InjectMocks
    private CalculatorService calculatorService = new CalculatorServiceImpl();

    @Mock
    private FileService fileService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void setUpTestWithEmptyResult() {
        Map<String, List<Transaction>> transactionMap = new HashMap<>();
        List<Transaction> paymentList = new ArrayList<>();
        List<Transaction> reversalList = new ArrayList<>();
        transactionMap.put(TransactionType.PAYMENT.getCode(), paymentList);
        transactionMap.put(TransactionType.REVERSAL.getCode(), reversalList);
        when(fileService.load()).thenReturn(transactionMap);
    }

    @Test
    public void testCalculateWithEmptyResult() {
        setUpTestWithEmptyResult();
        String expectedResult = "Relative balance for the period is: $0.00\n" +
                "Number of transactions included is: 0";
        Assert.assertEquals(expectedResult, calculatorService.calculate("", LocalDateTime.now(), LocalDateTime.now()));
    }

    private void setUpTestWithRepaymentAndReversalResult() {
        Map<String, List<Transaction>> transactionMap = new HashMap<>();
        List<Transaction> paymentList = new ArrayList<>();
        List<Transaction> reversalList = new ArrayList<>();
        Transaction transaction1 = Transaction.builder().transactionId("TX10001").fromAccountId("ACC334455").toAccountId("ACC778899")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 12:47:55")).amount(new BigDecimal(25.00))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction2 = Transaction.builder().transactionId("TX10002").fromAccountId("ACC998877").toAccountId("ACC334455")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 17:33:43")).amount(new BigDecimal(10.50))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction3 = Transaction.builder().transactionId("TX10003").fromAccountId("ACC998877").toAccountId("ACC334455")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 17:40:43")).amount(new BigDecimal(10.50))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction4 = Transaction.builder().transactionId("TX10004").fromAccountId("ACC998877").toAccountId("ACC334455")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 19:45:00")).amount(new BigDecimal(10.50))
                .transactionType(TransactionType.REVERSAL).relatedTransaction("TX10002").build();
        Transaction transaction5 = Transaction.builder().transactionId("TX10005").fromAccountId("ACC334455").toAccountId("ACC778899")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 21:47:55")).amount(new BigDecimal(25.00))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction6 = Transaction.builder().transactionId("TX10006").fromAccountId("ACC998877").toAccountId("ACC334455")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 22:33:43")).amount(new BigDecimal(10.50))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction7 = Transaction.builder().transactionId("TX10007").fromAccountId("ACC998877").toAccountId("ACC334455")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 23:40:43")).amount(new BigDecimal(10.50))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction8 = Transaction.builder().transactionId("TX10008").fromAccountId("ACC998877").toAccountId("ACC334455")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("21/10/2018 01:45:00")).amount(new BigDecimal(10.50))
                .transactionType(TransactionType.REVERSAL).relatedTransaction("TX10007").build();
        paymentList.add(transaction1);
        paymentList.add(transaction2);
        paymentList.add(transaction3);
        paymentList.add(transaction5);
        paymentList.add(transaction6);
        paymentList.add(transaction7);
        reversalList.add(transaction4);
        reversalList.add(transaction8);
        transactionMap.put(TransactionType.PAYMENT.getCode(), paymentList);
        transactionMap.put(TransactionType.REVERSAL.getCode(), reversalList);
        when(fileService.load()).thenReturn(transactionMap);
    }

    @Test
    public void testCalculateWithRepaymentAndReversalResult() {
        setUpTestWithRepaymentAndReversalResult();
        String expectedResultAcc1 = "Relative balance for the period is: -$29.00\n" +
                "Number of transactions included is: 4";
        Assert.assertEquals(expectedResultAcc1, calculatorService.calculate("ACC334455", ServiceUtil.convertStringToLocalDateTime("20/10/2018 12:00:00"),
                ServiceUtil.convertStringToLocalDateTime("20/10/2018 23:50:00")));
        String expectedResultAcc2 = "Relative balance for the period is: $50.00\n" +
                "Number of transactions included is: 2";
        Assert.assertEquals(expectedResultAcc2, calculatorService.calculate("ACC778899", ServiceUtil.convertStringToLocalDateTime("20/10/2018 12:00:00"),
                ServiceUtil.convertStringToLocalDateTime("20/10/2018 23:50:00")));
        String expectedResultAcc3 = "Relative balance for the period is: -$21.00\n" +
                "Number of transactions included is: 2";
        Assert.assertEquals(expectedResultAcc3, calculatorService.calculate("ACC998877", ServiceUtil.convertStringToLocalDateTime("20/10/2018 12:00:00"),
                ServiceUtil.convertStringToLocalDateTime("20/10/2018 23:50:00")));
    }

    private void setUpTestWithRepaymentResult() {
        Map<String, List<Transaction>> transactionMap = new HashMap<>();
        List<Transaction> paymentList = new ArrayList<>();
        List<Transaction> reversalList = new ArrayList<>();
        Transaction transaction1 = Transaction.builder().transactionId("TX10001").fromAccountId("ACC334455").toAccountId("ACC778899")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 12:47:55")).amount(new BigDecimal(25.00))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction2 = Transaction.builder().transactionId("TX10002").fromAccountId("ACC334455").toAccountId("ACC998877")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 17:33:43")).amount(new BigDecimal(10.50))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction3 = Transaction.builder().transactionId("TX10003").fromAccountId("ACC998877").toAccountId("ACC334455")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 17:40:43")).amount(new BigDecimal(50.50))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction4 = Transaction.builder().transactionId("TX10004").fromAccountId("ACC334455").toAccountId("ACC998877")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 19:45:00")).amount(new BigDecimal(10.50))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction5 = Transaction.builder().transactionId("TX10005").fromAccountId("ACC998877").toAccountId("ACC778899")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 20:40:43")).amount(new BigDecimal(10.50))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction6 = Transaction.builder().transactionId("TX10006").fromAccountId("ACC334455").toAccountId("ACC998877")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 21:45:00")).amount(new BigDecimal(10.50))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction7 = Transaction.builder().transactionId("TX10007").fromAccountId("ACC334455").toAccountId("ACC778899")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 22:40:43")).amount(new BigDecimal(50.50))
                .transactionType(TransactionType.PAYMENT).relatedTransaction("").build();
        Transaction transaction8 = Transaction.builder().transactionId("TX10008").fromAccountId("ACC778899").toAccountId("ACC998877")
                .createdAt(ServiceUtil.convertStringToLocalDateTime("20/10/2018 23:45:00")).amount(new BigDecimal(20.50))
                .transactionType(TransactionType.REVERSAL).relatedTransaction("").build();
        paymentList.add(transaction1);
        paymentList.add(transaction2);
        paymentList.add(transaction3);
        paymentList.add(transaction4);
        paymentList.add(transaction5);
        paymentList.add(transaction6);
        paymentList.add(transaction7);
        paymentList.add(transaction8);
        transactionMap.put(TransactionType.PAYMENT.getCode(), paymentList);
        transactionMap.put(TransactionType.REVERSAL.getCode(), reversalList);
        when(fileService.load()).thenReturn(transactionMap);
    }

    @Test
    public void testCalculateWithRepaymentResult() {
        setUpTestWithRepaymentResult();
        String expectedResultAcc1 = "Relative balance for the period is: -$56.50\n" +
                "Number of transactions included is: 6";
        Assert.assertEquals(expectedResultAcc1, calculatorService.calculate("ACC334455", ServiceUtil.convertStringToLocalDateTime("20/10/2018 12:00:00"),
                ServiceUtil.convertStringToLocalDateTime("20/10/2018 23:50:00")));
        String expectedResultAcc2 = "Relative balance for the period is: -$9.00\n" +
                "Number of transactions included is: 6";
        Assert.assertEquals(expectedResultAcc2, calculatorService.calculate("ACC998877", ServiceUtil.convertStringToLocalDateTime("20/10/2018 12:00:00"),
                ServiceUtil.convertStringToLocalDateTime("20/10/2018 23:50:00")));
        String expectedResultAcc3 = "Relative balance for the period is: $65.50\n" +
                "Number of transactions included is: 4";
        Assert.assertEquals(expectedResultAcc3, calculatorService.calculate("ACC778899", ServiceUtil.convertStringToLocalDateTime("20/10/2018 12:00:00"),
                ServiceUtil.convertStringToLocalDateTime("20/10/2018 23:50:00")));
    }
}
