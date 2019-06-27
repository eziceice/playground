package com.myob.codeexercise.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Unit test for payslip calculator
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CalculatorServiceTest {

    @Autowired
    private CalculatorService calculatorService;

    @Test
    public void testCalculateGrossIncome() {
        BigDecimal grossIncome = calculatorService.calculateGrossIncome(new BigDecimal(50000));
        Assert.assertEquals(new BigDecimal(4167), grossIncome);
    }

    @Test
    public void testCalculateIncomeTax() {
        BigDecimal taxIncome1 = calculatorService.calculateIncomeTax(new BigDecimal(18200));
        BigDecimal taxIncome2 = calculatorService.calculateIncomeTax(new BigDecimal(37000));
        BigDecimal taxIncome3 = calculatorService.calculateIncomeTax(new BigDecimal(87000));
        BigDecimal taxIncome4 = calculatorService.calculateIncomeTax(new BigDecimal(180000));
        BigDecimal taxIncome5 = calculatorService.calculateIncomeTax(new BigDecimal(250000));

        Assert.assertEquals(BigDecimal.ZERO, taxIncome1);
        Assert.assertEquals(new BigDecimal(298), taxIncome2);
        Assert.assertEquals(new BigDecimal(1652), taxIncome3);
        Assert.assertEquals(new BigDecimal(4519), taxIncome4);
        Assert.assertEquals(new BigDecimal(7144), taxIncome5);
    }

    @Test
    public void testCalculateNetIncome() {
        BigDecimal netIncome = calculatorService.calculateNetIncome(new BigDecimal(50000), new BigDecimal(10000));
        Assert.assertEquals(new BigDecimal(40000), netIncome);
    }

    @Test
    public void testCalculateSuperIncome() {
        BigDecimal superIncome = calculatorService.calculateSuperIncome(new BigDecimal(50000), 0.02d);
        Assert.assertEquals(new BigDecimal(1000), superIncome);
    }
}
