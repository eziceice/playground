package com.myob.codeexercise.service;

import com.myob.codeexercise.pojo.Salary;
import com.myob.codeexercise.properties.TaxProperties;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Implementation for tax calculator service
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {
    private BigDecimal MONTH;

    @Autowired
    private KieContainer kContainer;

    public CalculatorServiceImpl(@Autowired TaxProperties tax) {
        MONTH = new BigDecimal(tax.getMonth());
    }

    @Override
    public BigDecimal calculateGrossIncome(BigDecimal annualSalary) {
        return annualSalary.divide(MONTH, 0, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal calculateIncomeTax(BigDecimal annualSalary) {
        KieSession kieSession = kContainer.newKieSession();
        Salary salary = Salary.builder().annualSalary(annualSalary).build();
        kieSession.insert(salary);
        kieSession.fireAllRules();
        kieSession.dispose();
        return salary.getIncomeTax();
    }

    @Override
    public BigDecimal calculateNetIncome(BigDecimal grossIncome, BigDecimal incomeTax) {
        return grossIncome.subtract(incomeTax);
    }

    @Override
    public BigDecimal calculateSuperIncome(BigDecimal grossIncome, double superRate) {
        return grossIncome.multiply(BigDecimal.valueOf(superRate)).setScale(0, BigDecimal.ROUND_HALF_UP);
    }
}
