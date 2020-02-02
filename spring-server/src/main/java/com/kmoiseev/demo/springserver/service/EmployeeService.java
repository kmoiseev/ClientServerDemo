package com.kmoiseev.demo.springserver.service;

import com.kmoiseev.demo.springserver.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    Iterable<Employee> getAll();
    Employee updateSalary(Integer id, Long salary);
    void deleteAll();
}
