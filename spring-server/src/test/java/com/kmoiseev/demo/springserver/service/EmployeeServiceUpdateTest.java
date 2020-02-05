package com.kmoiseev.demo.springserver.service;

import com.kmoiseev.demo.springserver.exception.ModelNotFoundException;
import com.kmoiseev.demo.springserver.exception.ModelValidationException;
import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.model.EmployeeTestCreator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.kmoiseev.demo.springserver.model.EmployeeTestValidator.assertEmployeeIsCorrect;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceUpdateTest extends EmployeeServiceTestBase {

    @Test
    void updateEmployeeThrowsModelNotFound() {
        Integer id = 123;
        Long salary = 213341L;
        repositoryMocker.mockFindByIdReturnsEmptyOptional(id);

        assertThrows(ModelNotFoundException.class, () -> service.updateSalary(id, salary));
    }

    @Test
    void updateEmployeeThrowsModelValidation() {
        Integer id = 123;
        Long salaryOld = 2881L;
        Long salaryNew = -213341L;
        repositoryMocker.mockFindByIdReturns(id, EmployeeTestCreator.create(id, "Does not matter", salaryOld));

        assertThrows(ModelValidationException.class, () -> service.updateSalary(id, salaryNew));
    }

    @Test
    void updateSalarySavesEmployeeWithCorrectIdAndSalary() {
        Integer id = 123;
        String name = "Matter";
        Long salaryOld = 2881L;
        Long salaryNew = 213341L;
        repositoryMocker.mockFindByIdReturns(id, EmployeeTestCreator.create(id, name, salaryOld));

        service.updateSalary(id, salaryNew);

        Employee employeeSaved = repositoryMocker.captureOnceSavedEmployee();

        assertEmployeeIsCorrect(employeeSaved, id, name, salaryNew);
    }

    @Test
    void updateSalaryReturnsEmployeeWithCorrectIdAndSalary() {
        Integer id = 123;
        String name = "Matter";
        Long salaryOld = 2881L;
        Long salaryNew = 213341L;
        repositoryMocker.mockFindByIdReturns(id, EmployeeTestCreator.create(id, name, salaryOld));
        repositoryMocker.mockSavePassThroughEmployee();

        Employee employeeReturned = service.updateSalary(id, salaryNew);

        assertEmployeeIsCorrect(employeeReturned, id, name, salaryNew);
    }

}
