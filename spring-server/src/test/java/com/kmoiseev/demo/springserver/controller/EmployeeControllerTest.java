package com.kmoiseev.demo.springserver.controller;

import com.kmoiseev.demo.springserver.exception.ModelValidationException;
import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.model.EmployeeTestCreator;
import com.kmoiseev.demo.springserver.service.EmployeeService;
import com.kmoiseev.demo.springserver.service.EmployeeServiceTest;
import com.kmoiseev.demo.springserver.service.EmployeeServiceMocker;
import com.kmoiseev.demo.springserver.view.EmployeeOutputViewTestValidator;
import com.kmoiseev.demo.springserver.view.input.EmployeeInputView;
import com.kmoiseev.demo.springserver.view.output.EmployeeOutputView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.opentest4j.AssertionFailedError;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.kmoiseev.demo.springserver.view.EmployeeOutputViewTestValidator.assertEmployeeViewIsCorrect;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EmployeeControllerTest {

    private EmployeeController controller;
    private EmployeeServiceMocker employeeServiceMocker;

    @BeforeEach
    void initializeControllerWithMocks() {
        EmployeeService employeeService = mock(EmployeeService.class);

        employeeServiceMocker = new EmployeeServiceMocker(employeeService);
        controller = new EmployeeController(employeeService);
    }

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

    @Test
    void updatedEmployeeCorrectlyMapped() {
        Integer employeeId = 21441;
        Long salary = 219921L;
        String name = "someName";
        employeeServiceMocker.mockUpdatePassThroughIdAndSalaryWithName(name);

        EmployeeOutputView outputView = controller.updateEmployeeSalary(employeeId, salary);

        assertEmployeeViewIsCorrect(outputView, employeeId, name, salary);
    }

    @Test
    void getAllEmployeesCorrectlyMapped() {
        Integer employeeId = 213;
        String name = "someName";
        Long salary = 21234L;
        Employee employee = EmployeeTestCreator.create(employeeId, name, salary);
        employeeServiceMocker.mockGetAllReturnsEmployees(Collections.singleton(employee));

        Stream<EmployeeOutputView> employeeStream = controller.getAllSortById();
        EmployeeOutputView outputView = employeeStream
                .findFirst()
                .orElseThrow(() -> new AssertionFailedError("Single employee must present"));

        assertEmployeeViewIsCorrect(outputView, employeeId, name, salary);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    private static class EmployeeSortingTestEntry {
        private final Employee employee;
        private final int expectedPositionAfterSort;
    }

    private static EmployeeSortingTestEntry sortTestEntry(Integer employeeId, int expectedOrderAfterSort) {
        return new EmployeeSortingTestEntry(
                EmployeeTestCreator.create(employeeId, "ValidName", 0L),
                expectedOrderAfterSort
        );
    }

    private static Stream<List<EmployeeSortingTestEntry>> prepareSortingTestScenarios() {
        return Stream.of(
                Arrays.asList(
                        sortTestEntry(5, 2),
                        sortTestEntry(1, 0),
                        sortTestEntry(3, 1)
                ),
                Arrays.asList(
                        sortTestEntry(10, 0),
                        sortTestEntry(22, 2),
                        sortTestEntry(13, 1)
                ),
                Arrays.asList(
                        sortTestEntry(63, 2),
                        sortTestEntry(5, 1),
                        sortTestEntry(1, 0)
                ),
                Arrays.asList(
                        sortTestEntry(24, 0),
                        sortTestEntry(96, 1),
                        sortTestEntry(131, 2)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("prepareSortingTestScenarios")
    void getAllEmployeesCorrectlySorted(List<EmployeeSortingTestEntry> sortingTestEntries) {
        employeeServiceMocker.mockGetAllReturnsEmployees(
                sortingTestEntries.stream()
                        .map(EmployeeSortingTestEntry::getEmployee)
                        .collect(Collectors.toList())
        );

        List<EmployeeOutputView> listToVerify = controller.getAllSortById().collect(Collectors.toList());

        for (int i = 0; i < listToVerify.size(); ++i) {
            Integer employeeId = listToVerify.get(i).getId();
            int expectedPosition = sortingTestEntries.stream()
                    .filter(employeeSortingTestEntry ->
                            employeeSortingTestEntry.employee.getId().equals(employeeId)
                    ).findFirst()
                    .orElseThrow(() -> new AssertionFailedError("Employee " + employeeId + " is unknown"))
                    .expectedPositionAfterSort;
            assertEquals(expectedPosition, i, "Position is not correct for employee with id " + employeeId);
        }
    }

    @Test
    void deleteAllServiceMethodGetsCalled() {
        controller.deleteAll();

        employeeServiceMocker.verifyDeleteAllCalledOnce();
    }
}
