package com.myob.codeexercise.service;

import com.myob.codeexercise.pojo.Employee;
import com.myob.codeexercise.pojo.Salary;
import com.myob.codeexercise.utils.ServiceUtil;
import com.myob.codeexercise.validator.SalaryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation for file service
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${employee.salary.details.path}")
    private String salaryFilePath;

    @Value("${employee.payslip.details.path}")
    private String paySlipFilePath;

    private volatile List<Salary> salaryList;

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private SalaryValidator salaryValidator;

    /**
     * Map String to Salary
     */
    private Function<String, Salary> mapToSalary = line -> {
        String[] p = line.split(",");
        if (salaryValidator.isValidName(p[0])) {
            Employee employee = Employee.builder().firstName(p[0]).lastName(p[1]).build();
            double superRate = Double.valueOf(p[3].replace("%", "")) / 100;
            BigDecimal annualSalary = new BigDecimal(p[2]);
            BigDecimal grossIncome = calculatorService.calculateGrossIncome(annualSalary);
            BigDecimal incomeTax = calculatorService.calculateIncomeTax(annualSalary);
            return Salary.builder().employee(employee).annualSalary(new BigDecimal(p[2]))
                    .superRate(superRate).paymentStartDate(p[4]).grossIncome(grossIncome)
                    .incomeTax(incomeTax).netIncome(calculatorService.calculateNetIncome(grossIncome, incomeTax)).payPeriod(p[4])
                    .superIncome(calculatorService.calculateSuperIncome(grossIncome, superRate)).build();
        }
        return new Salary();
    };

    @Override
    public List<Salary> load() {
        // Ensure only one instance will be created - thread safe
        if (salaryList == null) {
            synchronized (FileServiceImpl.class) {
                if (salaryList == null) {
                    try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(salaryFilePath))) {
                        salaryList = bufferedReader.lines().skip(1).map(mapToSalary).collect(Collectors.toList());
                    } catch (IOException e) {
                        System.out.println("Sorry, unexpected exception happened while loading employee salary details. Exit...");
                        System.exit(1); // Exit
                    }
                }
            }
        }
        return salaryList;
    }

    @Override
    public void write(List<Salary> salaryList) {
        File paySlipFile = new File(paySlipFilePath);
        try (PrintWriter pw = new PrintWriter(paySlipFile)) {
            pw.println(ServiceUtil.convertSalaryToOutputString(salaryList));
        } catch (IOException e) {
            System.out.println("Sorry, unexpected exception happened while creating employee payslip details. Exit...");
            System.exit(1); // Exit
        }
    }
}
