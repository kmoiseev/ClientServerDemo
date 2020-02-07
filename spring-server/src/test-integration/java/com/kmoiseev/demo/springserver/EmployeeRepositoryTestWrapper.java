package com.kmoiseev.demo.springserver;

import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.opentest4j.AssertionFailedError;

import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AllArgsConstructor
public class EmployeeRepositoryTestWrapper {

  private final EmployeeRepository repository;

  void clean() {
    repository.deleteAll();
  }

  Employee getFirstRepoEmployeeOrThrow() {
    return getAll().findFirst().orElseThrow(employeeNotPresentExceptionSupplier());
  }

  void assertRepoIsEmpty() {
    assertEquals(0, getAll().count(), "Expected no employees present in repository");
  }

  Employee persistEmployee(Employee employee) {
    return repository.save(employee);
  }

  Employee getEmployeeOrThrow(Integer id) {
    return repository.findById(id).orElseThrow(employeeNotPresentExceptionSupplier());
  }

  private Stream<Employee> getAll() {
    return StreamSupport.stream(repository.findAll().spliterator(), false);
  }

  private Supplier<AssertionFailedError> employeeNotPresentExceptionSupplier() {
    return () -> new AssertionFailedError("Employee must present in repository");
  }
}
