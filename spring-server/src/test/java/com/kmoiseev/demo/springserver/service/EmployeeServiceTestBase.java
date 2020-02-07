package com.kmoiseev.demo.springserver.service;

import com.kmoiseev.demo.springserver.repository.EmployeeRepository;
import com.kmoiseev.demo.springserver.repository.mocker.EmployeeRepositoryMocker;
import com.kmoiseev.demo.springserver.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public abstract class EmployeeServiceTestBase {

  EmployeeService service;
  EmployeeRepositoryMocker repositoryMocker;

  @BeforeEach
  void initializeControllerWithMocks() {
    EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

    repositoryMocker = new EmployeeRepositoryMocker(employeeRepository);

    service = new EmployeeServiceImpl(employeeRepository);
  }
}
