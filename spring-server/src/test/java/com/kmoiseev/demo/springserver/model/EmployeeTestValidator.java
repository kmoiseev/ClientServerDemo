package com.kmoiseev.demo.springserver.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTestValidator {
    public static void assertEmployeeIsCorrect(Employee employee, String name, Long salary) {
        assertEquals(name, employee.getName(), "Employee name is not correct");
        assertEquals(salary, employee.getSalary().getAmount(), "Saved employee salary is not correct");
    }

    public static void assertEmployeeIsCorrect(Employee employee, Integer id, String name, Long salary) {
        assertEquals(id, employee.getId(), "Employee id is not correct");
        assertEmployeeIsCorrect(employee, name, salary);
    }
}
