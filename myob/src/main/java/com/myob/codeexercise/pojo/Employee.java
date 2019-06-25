package com.myob.codeexercise.pojo;

import lombok.*;

/**
 * POJO for employee
 */
@Getter
@Builder
@ToString
public class Employee {
    private String firstName;

    private String lastName;

    public String toCSVString() {
        return firstName + " " + lastName;
    }
}
