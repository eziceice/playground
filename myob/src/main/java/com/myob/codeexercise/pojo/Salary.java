package com.myob.codeexercise.pojo;

import lombok.*;

import java.math.BigDecimal;

/**
 * POJO for salary
 */
@Getter
@ToString
@Builder
@Setter
@NoArgsConstructor
public class Salary {

    private Employee employee;

    private BigDecimal annualSalary;

    private double superRate;

    private String paymentStartDate;

    private BigDecimal incomeTax;

    private BigDecimal netIncome;

    private BigDecimal superIncome;

    private BigDecimal grossIncome;

    //TODO: payPeriod is supposed to be as same as paymentStartDate?
    private String payPeriod;
}
