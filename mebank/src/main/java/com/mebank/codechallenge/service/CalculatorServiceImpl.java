package com.mebank.codechallenge.service;

import com.mebank.codechallenge.enumeration.TransactionType;
import com.mebank.codechallenge.pojo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Implementation for Calculator Service
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Autowired
    private FileService fileService;

    /**
     * Calculate Service based on account Id and time range.
     *
     * @param accountId
     * @param from
     * @param to
     * @return
     */
    @Override
    public String calculate(String accountId, LocalDateTime from, LocalDateTime to) {
        Map<String, List<Transaction>> transactionMap = fileService.load();
        List<Transaction> paymentList = transactionMap.get(TransactionType.PAYMENT.getCode());
        List<Transaction> reversalList = transactionMap.get(TransactionType.REVERSAL.getCode());
        List<Transaction> accountPayList = paymentList.stream().filter(isApplicableTransaction(from, to, reversalList)).filter(t -> t.getFromAccountId().equals(accountId)).collect(Collectors.toList());
        List<Transaction> accountReceiveList = paymentList.stream().filter(isApplicableTransaction(from, to, reversalList)).filter(t -> t.getToAccountId().equals(accountId)).collect(Collectors.toList());

        BigDecimal received = accountReceiveList.stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal paid = accountPayList.stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::subtract);
        BigDecimal total = received.add(paid).setScale(2, BigDecimal.ROUND_HALF_UP);

        return printResult(String.valueOf(accountPayList.size() + accountReceiveList.size()), total);
    }

    /**
     * Generate result string
     *
     * @param transactions
     * @param balance
     * @return
     */
    private String printResult(String transactions, BigDecimal balance) {
        StringBuilder sb = new StringBuilder("Relative balance for the period is: ");
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            sb.append("-$");
        } else {
            sb.append("$");
        }
        sb.append(balance.abs());
        sb.append("\n");
        sb.append("Number of transactions included is: ");
        sb.append(transactions);
        return sb.toString();
    }

    /**
     * Filter For all applicable Transactions
     *
     * @param from
     * @param to
     * @param reversalList
     * @return
     */
    private Predicate<Transaction> isApplicableTransaction(LocalDateTime from, LocalDateTime to, List<Transaction> reversalList) {
        return t -> (t.getCreatedAt().isAfter(from) || t.getCreatedAt().isEqual(from)) && (t.getCreatedAt().isBefore(to) || t.getCreatedAt().isEqual(to))
                && reversalList.stream().noneMatch(i -> i.getRelatedTransaction().equals(t.getTransactionId()));
    }
}
