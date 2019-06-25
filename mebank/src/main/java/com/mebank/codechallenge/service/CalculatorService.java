package com.mebank.codechallenge.service;

import java.time.LocalDateTime;

/**
 * Interface for Calculator Service
 */
public interface CalculatorService {
    String calculate(String accountId, LocalDateTime from, LocalDateTime to);
}
