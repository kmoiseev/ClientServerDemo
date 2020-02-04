package com.kmoiseev.demo.springserver.controller;

import com.kmoiseev.demo.springserver.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

    private EmployeeController controller;

    private EmployeeService service;

    @BeforeEach
    void initializeControllerWithMocks() {
        service = mock(EmployeeService.class);

        controller = new EmployeeController(service);
    }

    @Test
    void createdUserCorrectlyMapped() {
        when(service.create());
    }

}
