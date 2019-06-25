package com.myob.codeexercise.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Unit test for file service
 */
@SpringBootTest()
@RunWith(SpringRunner.class)
public class FileServiceTest {

    private FileService fileService = new FileServiceImpl();

    private String paySlipFilePath = "./src/test/resources/employeepayslipsdetails-test.csv";

    private String salaryFilePath = "./src/test/resources/employeesalarydetails-test.csv";

    private String invalidSalaryFilePath = "./src/test/resources/employeesalarydetails-test-invalid.csv";

    @Autowired
    private CalculatorService calculatorService;


    public void setUp() {
        ReflectionTestUtils.setField(fileService, "paySlipFilePath", paySlipFilePath);
        ReflectionTestUtils.setField(fileService, "salaryFilePath", salaryFilePath);
        ReflectionTestUtils.setField(fileService, "calculatorService", calculatorService);
    }

    @Test
    public void testLoad() {
        setUp();
        Assert.assertEquals(3, fileService.load().size());
    }

    @Test
    public void testWrite() throws IOException {
        setUp();
        String expectedResult = "name, pay period, gross income, income tax, net income, super\n" +
                "David Rudd,01 March – 31 March,5004,922,4082,450\n" +
                "Ryan Chen,01 March – 31 March,10000,2669,7331,1000\n" +
                "Yutian Li,01 March – 31 March,20833,7144,13689,2083";
        fileService.write(fileService.load());
        Assert.assertEquals(expectedResult, readFile().trim());
    }

    @Test
    public void testLoanWithInvalidInput()
    {
        setUp();
        ReflectionTestUtils.setField(fileService, "salaryFilePath", invalidSalaryFilePath);
        Assert.assertEquals(2, fileService.load().size());
    }

    private String readFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(paySlipFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
