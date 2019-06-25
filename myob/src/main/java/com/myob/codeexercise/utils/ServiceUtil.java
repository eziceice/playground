package com.myob.codeexercise.utils;

import com.myob.codeexercise.pojo.Salary;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Util class for service
 */
public class ServiceUtil {

    /**
     * Convert a list of salary object to a CSV string
     *
     * @param salaryList
     * @return a CSV String
     */
    public static String convertSalaryToOutputString(List<Salary> salaryList) {
        String header = "name, pay period, gross income, income tax, net income, super" + "\n";
        Function<Salary, String> salaryStringFunction = salary -> salary.getEmployee().toCSVString() + "," + salary.getPayPeriod() + "," + salary.getGrossIncome() +
                "," + salary.getIncomeTax() + "," + salary.getNetIncome() + "," + salary.getSuperIncome();
        return header + Optional.ofNullable(salaryList)
                .orElseGet(Collections::emptyList).stream().filter(Objects::nonNull)
                .map(salaryStringFunction).collect(Collectors.joining("\n"));
    }
}
