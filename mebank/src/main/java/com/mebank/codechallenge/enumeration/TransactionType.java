package com.mebank.codechallenge.enumeration;

import lombok.Getter;

/**
 * Transaction Type Enumeration
 */
@Getter
public enum TransactionType {
    PAYMENT("PAYMENT"),
    REVERSAL("REVERSAL");

    private String code;

    TransactionType(String code) {
        this.code = code;
    }
}
