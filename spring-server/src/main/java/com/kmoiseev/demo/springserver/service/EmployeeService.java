package com.kmoiseev.demo.springserver.service;

import com.kmoiseev.demo.springserver.model.Employee;

public interface EmployeeService {
  Employee create(Employee employee);

  Employee updateSalary(Integer id, Long salary);

  Iterable<Employee> getAll();

  void deleteAll();
}
