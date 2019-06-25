package com.myob.codeexercise.validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaryValidatorTest {

    @Autowired
    private SalaryValidator salaryValidator;


    @Test
    public void testIsValidName(){
        Assert.assertEquals(false, salaryValidator.isValidName(""));
        Assert.assertEquals(false, salaryValidator.isValidName("9128312931"));
        Assert.assertEquals(true, salaryValidator.isValidName("Yutian"));
    }
}
