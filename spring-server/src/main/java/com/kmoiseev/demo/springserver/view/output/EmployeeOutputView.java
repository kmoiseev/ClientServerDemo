package com.kmoiseev.demo.springserver.view.output;

import com.kmoiseev.demo.springserver.model.Employee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EmployeeOutputView {
    private final Integer id;
    private final String name;
    private final Integer salary;

    public static EmployeeOutputView of(Employee employee) {
        return new EmployeeOutputView(
                employee.getId(),
                employee.getName(),
                employee.getSalary().getAmount()
        );
    }
}
