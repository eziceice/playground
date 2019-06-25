package com.myob.codeexercise.service;

import java.math.BigDecimal;

/**
 * Interface for calculator service
 */
public interface CalculatorService {
    BigDecimal calculateGrossIncome(BigDecimal annualSalary);

    BigDecimal calculateIncomeTax(BigDecimal annualSalary);

    BigDecimal calculateNetIncome(BigDecimal grossIncome, BigDecimal incomeTax);

    BigDecimal calculateSuperIncome(BigDecimal grossIncome, double superRate);
}
