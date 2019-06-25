package com.mebank.codechallenge.service;

import com.mebank.codechallenge.enumeration.TransactionType;
import com.mebank.codechallenge.pojo.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Map;

/**
 * Unit test for File Service
 */
@RunWith(SpringRunner.class)
public class FileServiceTest {

    @TestConfiguration
    static class FileServiceImplTestContextConfiguration {

        @Bean
        public FileService fileService() {
            return new FileServiceImpl();
        }
    }

    @Autowired
    private FileService fileService;

    @Test
    public void testLoad() {
        ReflectionTestUtils.setField(fileService, "path", "./src/test/resources/transactionhistory.csv");
        Map<String, List<Transaction>> result = fileService.load();

        List<Transaction> paymentList = result.get(TransactionType.PAYMENT.getCode());
        List<Transaction> reversalList = result.get(TransactionType.REVERSAL.getCode());

        Assert.assertEquals(paymentList.size(), 10);
        Assert.assertEquals(reversalList.size(), 2);
        Assert.assertEquals(paymentList.get(0).getTransactionId(), "TX10001");
        Assert.assertEquals(paymentList.get(1).getToAccountId(), "ACC998877");
        Assert.assertEquals(reversalList.get(0).getRelatedTransaction(), "TX10002");
        Assert.assertEquals(reversalList.get(1).getRelatedTransaction(), "TX10006");
    }
}
