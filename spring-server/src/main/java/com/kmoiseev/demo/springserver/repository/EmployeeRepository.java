package com.kmoiseev.demo.springserver.repository;

import com.kmoiseev.demo.springserver.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
}
