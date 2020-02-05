package com.kmoiseev.demo.springserver.controller;

import com.kmoiseev.demo.springserver.service.EmployeeService;
import com.kmoiseev.demo.springserver.service.mocker.EmployeeServiceMocker;
import com.kmoiseev.demo.springserver.view.output.EmployeeOutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.kmoiseev.demo.springserver.view.EmployeeOutputViewTestValidator.assertEmployeeViewIsCorrect;
import static org.mockito.Mockito.mock;

public class EmployeeControllerUpdateTest extends EmployeeControllerTestBase {

    @BeforeEach
    void initializeControllerWithMocks() {
        EmployeeService employeeService = mock(EmployeeService.class);

        employeeServiceMocker = new EmployeeServiceMocker(employeeService);
        controller = new EmployeeController(employeeService);
    }


    @Test
    void updatedEmployeeCorrectlyMapped() {
        Integer employeeId = 21441;
        Long salary = 219921L;
        String name = "someName";
        employeeServiceMocker.mockUpdatePassThroughIdAndSalaryWithName(name);

        EmployeeOutputView outputView = controller.updateEmployeeSalary(employeeId, salary);

        assertEmployeeViewIsCorrect(outputView, employeeId, name, salary);
    }
}
