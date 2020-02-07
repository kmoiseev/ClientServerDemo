package com.kmoiseev.demo.springserver.controller;

import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.model.EmployeeTestCreator;
import com.kmoiseev.demo.springserver.view.output.EmployeeOutputView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

public class EmployeeControllerGetAllTest extends EmployeeControllerTestBase {

  private static EmployeeSortingTestEntry sortTestEntry(
      Integer employeeId, int expectedOrderAfterSort) {
    return new EmployeeSortingTestEntry(
        EmployeeTestCreator.create(employeeId, "ValidName", 0L), expectedOrderAfterSort);
  }

  private static Stream<List<EmployeeSortingTestEntry>> prepareSortingTestScenarios() {
    return Stream.of(
        Arrays.asList(sortTestEntry(5, 2), sortTestEntry(1, 0), sortTestEntry(3, 1)),
        Arrays.asList(sortTestEntry(10, 0), sortTestEntry(22, 2), sortTestEntry(13, 1)),
        Arrays.asList(sortTestEntry(63, 2), sortTestEntry(5, 1), sortTestEntry(1, 0)),
        Arrays.asList(sortTestEntry(24, 0), sortTestEntry(96, 1), sortTestEntry(131, 2)));
  }

  @Test
  void getAllEmployeesCorrectlyMapped() {
    Integer employeeId = 213;
    String name = "someName";
    Long salary = 21234L;
    Employee employee = EmployeeTestCreator.create(employeeId, name, salary);
    employeeServiceMocker.mockGetAllReturnsEmployees(Collections.singleton(employee));

    Stream<EmployeeOutputView> employeeStream = controller.getAllSortById();
    EmployeeOutputView outputView =
        employeeStream
            .findFirst()
            .orElseThrow(() -> new AssertionFailedError("Single employee must present"));

    assertEmployeeViewIsCorrect(outputView, employeeId, name, salary);
  }

  @ParameterizedTest
  @MethodSource("prepareSortingTestScenarios")
  void getAllEmployeesCorrectlySorted(List<EmployeeSortingTestEntry> sortingTestEntries) {
    employeeServiceMocker.mockGetAllReturnsEmployees(
        sortingTestEntries.stream()
            .map(EmployeeSortingTestEntry::getEmployee)
            .collect(Collectors.toList()));

    List<EmployeeOutputView> listToVerify =
        controller.getAllSortById().collect(Collectors.toList());

    for (int i = 0; i < listToVerify.size(); ++i) {
      Integer employeeId = listToVerify.get(i).getId();
      int expectedPosition =
          sortingTestEntries.stream()
              .filter(
                  employeeSortingTestEntry ->
                      employeeSortingTestEntry.employee.getId().equals(employeeId))
              .findFirst()
              .orElseThrow(() -> new AssertionFailedError("Employee " + employeeId + " is unknown"))
              .expectedPositionAfterSort;
      assertEquals(
          expectedPosition, i, "Position is not correct for employee with id " + employeeId);
    }
  }

  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @Getter
  private static class EmployeeSortingTestEntry {
    private final Employee employee;
    private final int expectedPositionAfterSort;
  }
}
