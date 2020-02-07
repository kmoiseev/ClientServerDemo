package com.kmoiseev.demo.springserver.service;

import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.model.EmployeeTestCreator;
import org.junit.jupiter.api.Test;

import static com.kmoiseev.demo.springserver.model.EmployeeTestValidator.assertEmployeeIsCorrect;

public class EmployeeServiceSaveTest extends EmployeeServiceTestBase {

  @Test
  void saveEmployeeKeepsEmployeeUnmodified() {
    Employee employee = EmployeeTestCreator.create(212, "Jacob", 76581L);
    repositoryMocker.mockSavePassThroughEmployee();

    Employee employeeCreated = service.create(employee);

    assertEmployeeIsCorrect(
        employeeCreated, employee.getId(), employee.getName(), employee.getSalary().getAmount());
  }

  @Test
  void saveRepositoryMethodGetsCalled() {
    Employee employee = EmployeeTestCreator.create(212, "Jacob", 76581L);

    service.create(employee);

    repositoryMocker.verifySaveCalledOnceWith(employee);
  }
}
