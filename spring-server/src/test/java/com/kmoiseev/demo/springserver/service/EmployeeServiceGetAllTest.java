package com.kmoiseev.demo.springserver.service;

import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.model.EmployeeTestCreator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceGetAllTest extends EmployeeServiceTestBase {

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

}
