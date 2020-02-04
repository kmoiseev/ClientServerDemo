package com.kmoiseev.demo.springserver.service;

import com.kmoiseev.demo.springserver.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee updateSalary(Integer id, Long salary);
    Iterable<Employee> getAll();
    void deleteAll();
}
