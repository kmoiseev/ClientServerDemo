package com.kmoiseev.demo.springserver.service;

import com.kmoiseev.demo.springserver.exception.ModelNotFoundException;
import com.kmoiseev.demo.springserver.exception.ModelValidationException;
import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.model.EmployeeTestCreator;
import com.kmoiseev.demo.springserver.repository.EmployeeRepository;
import com.kmoiseev.demo.springserver.repository.EmployeeRepositoryMocker;
import com.kmoiseev.demo.springserver.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.kmoiseev.demo.springserver.model.EmployeeTestValidator.assertEmployeeIsCorrect;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {

    private EmployeeService service;
    private EmployeeRepositoryMocker repositoryMocker;

    @BeforeEach
    void initializeControllerWithMocks() {
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

        repositoryMocker = new EmployeeRepositoryMocker(employeeRepository);

        service = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void saveEmployeeKeepsEmployeeUnmodified() {
        Employee employee = EmployeeTestCreator.create(212, "Jacob", 76581L);
        repositoryMocker.mockSavePassThroughEmployee();

        Employee employeeCreated = service.create(employee);

        assertEmployeeIsCorrect(employeeCreated, employee.getId(), employee.getName(), employee.getSalary().getAmount());
    }

    @Test
    void saveRepositoryMethodGetsCalled() {
        Employee employee = EmployeeTestCreator.create(212, "Jacob", 76581L);

        service.create(employee);

        repositoryMocker.verifySaveCalledOnceWith(employee);
    }

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

    @Test
    void getAllReturnsRepositoryResultUnmodified() {
        List<Employee> employees = Arrays.asList(
                EmployeeTestCreator.create(21, "Semen", 212L),
                EmployeeTestCreator.create(12, "Gary", 4114141L)
        );
        repositoryMocker.mockGetAllReturns(employees);

        Iterable<Employee> employeesReturned = service.getAll();

        assertEquals(employees, employeesReturned, "getAll expected to forward iterable from repo");
    }

    @Test
    void deleteAllRepositoryMethodGetsCalledOnce() {
        service.deleteAll();

        repositoryMocker.verifyDeleteAllCalledOnce();
    }
}
