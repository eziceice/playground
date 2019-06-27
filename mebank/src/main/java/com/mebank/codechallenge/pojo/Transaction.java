package com.mebank.codechallenge.pojo;

import com.mebank.codechallenge.enumeration.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * POJO for Transaction
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Transaction {

    private String transactionId;

    private String fromAccountId;

    private String toAccountId;

    private LocalDateTime createdAt;

    private BigDecimal amount;

    private TransactionType transactionType;

    private String relatedTransaction;
}
