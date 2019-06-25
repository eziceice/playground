package com.myob.codeexercise.pojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

/**
 * Unit test for salary
 */
@RunWith(JUnit4.class)
public class SalaryTest {
    private Salary salary;

    @Before
    public void setUp() {
        Employee employee = Employee.builder().lastName("LI").firstName("YUTIAN").build();
        salary = Salary.builder().employee(employee).annualSalary(new BigDecimal("12345")).grossIncome(new BigDecimal("54321"))
                .incomeTax(new BigDecimal("666")).netIncome(new BigDecimal("555")).paymentStartDate("March 01 - March 31").payPeriod("March 01 - March 31")
                .superRate(5l).superIncome(new BigDecimal("777")).build();
    }


    @Test
    public void testSalaryGetters() {
        Assert.assertEquals("YUTIAN", salary.getEmployee().getFirstName());
        Assert.assertEquals("LI", salary.getEmployee().getLastName());
        Assert.assertEquals(new BigDecimal("12345"), salary.getAnnualSalary());
        Assert.assertEquals(new BigDecimal("54321"), salary.getGrossIncome());
        Assert.assertEquals(new BigDecimal("666"), salary.getIncomeTax());
        Assert.assertEquals(new BigDecimal("555"), salary.getNetIncome());
        Assert.assertEquals("March 01 - March 31", salary.getPaymentStartDate());
        Assert.assertEquals("March 01 - March 31", salary.getPayPeriod());
        Assert.assertEquals(5l, salary.getSuperRate(), 0);
        Assert.assertEquals(new BigDecimal("777"), salary.getSuperIncome());
    }

}
