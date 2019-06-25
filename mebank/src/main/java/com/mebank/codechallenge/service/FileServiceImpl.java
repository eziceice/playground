package com.mebank.codechallenge.service;

import com.mebank.codechallenge.enumeration.TransactionType;
import com.mebank.codechallenge.pojo.Transaction;
import com.mebank.codechallenge.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation for File Service
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${transaction.history.path}")
    private String path;

    private Map<String, List<Transaction>> resultMap;

    /**
     * Map String to Transaction
     */
    private Function<String, Transaction> mapToTransaction = (line) -> {
        String[] p = line.split(",");
        LocalDateTime date = ServiceUtil.convertStringToLocalDateTime(p[3]);
        TransactionType transactionType = TransactionType.valueOf(p[5]);
        String relatedTransaction = null;
        if (p.length == 7) {
            relatedTransaction = p[6];
        }
        return Transaction.builder().transactionId(p[0]).fromAccountId(p[1]).toAccountId(p[2]).createdAt(date).
                amount(new BigDecimal(p[4])).transactionType(transactionType).relatedTransaction(relatedTransaction).build();
    };

    /**
     * Load transaction history from external files
     *
     * @return
     */
    @Override
    public synchronized Map<String, List<Transaction>> load() {
        if (resultMap == null) {
            try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path))) {
                List<Transaction> transactionList = bufferedReader.lines().skip(1).map(mapToTransaction).collect(Collectors.toList());
                List<Transaction> paymentList = transactionList.stream().filter(t -> t.getTransactionType().equals(TransactionType.PAYMENT)).collect(Collectors.toList());
                List<Transaction> reversalList = transactionList.stream().filter(t -> t.getTransactionType().equals(TransactionType.REVERSAL)).collect(Collectors.toList());
                resultMap = new HashMap<>();
                resultMap.put(TransactionType.PAYMENT.getCode(), paymentList);
                resultMap.put(TransactionType.REVERSAL.getCode(), reversalList);
            } catch (IOException e) {
                System.out.println("Sorry, unexpected exception happened while loading the transaction history. Exit...");
                System.exit(0); // Exit
            }
        }
        return resultMap;
    }
}
