package com.myob.codeexercise.validator;

import org.springframework.stereotype.Component;

@Component
public class SalaryValidator {

    public boolean isValidName(String name)
    {
        return name.matches("[a-zA-Z]+");
    }

}
