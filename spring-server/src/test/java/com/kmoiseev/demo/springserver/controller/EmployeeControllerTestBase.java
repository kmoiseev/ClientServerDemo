package com.kmoiseev.demo.springserver.controller;

import com.kmoiseev.demo.springserver.service.EmployeeService;
import com.kmoiseev.demo.springserver.service.mocker.EmployeeServiceMocker;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public abstract class EmployeeControllerTestBase {
  EmployeeController controller;
  EmployeeServiceMocker employeeServiceMocker;

  @BeforeEach
  void initializeControllerWithMocks() {
    EmployeeService employeeService = mock(EmployeeService.class);

    employeeServiceMocker = new EmployeeServiceMocker(employeeService);
    controller = new EmployeeController(employeeService);
  }
}
