package com.kmoiseev.demo.springserver.service.impl;

import com.kmoiseev.demo.springserver.exception.ModelNotFoundException;
import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.model.EmployeeSalary;
import com.kmoiseev.demo.springserver.repository.EmployeeRepository;
import com.kmoiseev.demo.springserver.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository repository;

  @Override
  public Employee create(Employee employee) {
    return repository.save(employee);
  }

  @Override
  public Employee updateSalary(Integer id, Long salary) {
    Employee employee =
        repository
            .findById(id)
            .orElseThrow(
                () -> new ModelNotFoundException("Employee with id " + id + " does not exist"));

    return repository.save(employee.withSalary(EmployeeSalary.of(salary)));
  }

  @Override
  public Iterable<Employee> getAll() {
    return repository.findAll();
  }

  @Override
  public void deleteAll() {
    repository.deleteAll();
  }
}
