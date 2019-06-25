package com.myob.codeexercise.pojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit test for employee
 */
@RunWith(JUnit4.class)
public class EmployeeTest {

    private Employee employee;

    @Before
    public void setUp() {
        employee = Employee.builder().firstName("Yutian").lastName("Li").build();
    }

    @Test
    public void testEmployeeGetters() {
        Assert.assertEquals("Yutian", employee.getFirstName());
        Assert.assertEquals("Li", employee.getLastName());
    }

    @Test
    public void testEmployeToCSVString() {
        String expected = "Yutian Li";
        Assert.assertEquals(expected, employee.toCSVString());
    }
}
