package com.kmoiseev.demo.springserver.model;

import com.kmoiseev.demo.springserver.exception.ModelValidationException;
import com.kmoiseev.demo.springserver.view.input.EmployeeInputView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Employee {
    @Id
    @With
    private final Integer id;
    private final String name;
    @With
    private final EmployeeSalary salary;

    public static Employee of(EmployeeInputView inputView) {
        if (StringUtils.isEmpty(inputView.getName())) {
            throw new ModelValidationException("Employee name cannot be empty");
        }
        return new Employee(null, inputView.getName(), EmployeeSalary.of(inputView.getSalary()));
    }
}
