package com.kmoiseev.demo.springserver.service.impl;

import com.kmoiseev.demo.springserver.exception.ModelNotFoundException;
import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.model.EmployeeSalary;
import com.kmoiseev.demo.springserver.repository.EmployeeRepository;
import com.kmoiseev.demo.springserver.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee update(Integer id, String name, Long salary) {
        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ModelNotFoundException(
                                "Employee with id " + id + " does not exist"
                        )
                );

        if (Objects.nonNull(name)) {
            employee = employee.withName(name);
        }

        if (Objects.nonNull(salary)) {
            employee = employee.withSalary(EmployeeSalary.of(salary));
        }

        return repository.save(employee);
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
