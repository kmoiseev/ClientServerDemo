package com.kmoiseev.demo.springserver.controller;

import com.kmoiseev.demo.springserver.exception.ModelValidationException;
import com.kmoiseev.demo.springserver.view.input.EmployeeInputView;
import com.kmoiseev.demo.springserver.view.output.EmployeeOutputView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.kmoiseev.demo.springserver.view.EmployeeOutputViewTestValidator.assertEmployeeViewIsCorrect;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeControllerSaveTest extends EmployeeControllerTestBase {

    @Test
    void createdEmployeeCorrectlyMapped() {
        String name = "someName";
        Long salary = 2771L;
        EmployeeInputView inputView = new EmployeeInputView(name, salary);
        employeeServiceMocker.mockCreatePassThroughEmployee();

        EmployeeOutputView outputView = controller.create(inputView);

        assertEmployeeViewIsCorrect(outputView, name, salary);
    }

    private static Stream<EmployeeInputView> prepareModelValidationErrorEmployees() {
        return Stream.of(
                new EmployeeInputView("", 213L),
                new EmployeeInputView(null, 213L),
                new EmployeeInputView("validName", null),
                new EmployeeInputView("validName", -213L),
                new EmployeeInputView(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("prepareModelValidationErrorEmployees")
    void modelValidationErrorCreatingEmployee(EmployeeInputView inputView) {
        employeeServiceMocker.mockCreatePassThroughEmployee();

        assertThrows(ModelValidationException.class, () -> controller.create(inputView));
    }

}
