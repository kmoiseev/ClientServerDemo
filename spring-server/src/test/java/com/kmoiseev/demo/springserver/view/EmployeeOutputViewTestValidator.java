package com.kmoiseev.demo.springserver.view;

import com.kmoiseev.demo.springserver.view.output.EmployeeOutputView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeOutputViewTestValidator {
    public static void assertEmployeeViewIsCorrect(EmployeeOutputView employeeOutputView, String name, Long salary) {
        assertEquals(name, employeeOutputView.getName(), "Employee output view name is not correct");
        assertEquals(salary, employeeOutputView.getSalary(), "Employee output view salary is not correct");
    }

    public static void assertEmployeeViewIsCorrect(EmployeeOutputView employeeOutputView, Integer id, String name, Long salary) {
        assertEquals(id, employeeOutputView.getId(), "Employee output view id is not correct");
        assertEmployeeViewIsCorrect(employeeOutputView, name, salary);
    }
}
