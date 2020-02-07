package com.kmoiseev.demo.springserver;

import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.repository.EmployeeRepository;
import com.kmoiseev.demo.springserver.view.input.EmployeeInputView;
import com.kmoiseev.demo.springserver.view.output.EmployeeOutputView;
import com.kmoiseev.demo.springserver.view.output.ErrorView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.kmoiseev.demo.springserver.ResponseValidator.assertResponseBodyIsNotEmpty;
import static com.kmoiseev.demo.springserver.ResponseValidator.assertStatusCodeEquals;
import static com.kmoiseev.demo.springserver.model.EmployeeTestValidator.assertEmployeeIsCorrect;
import static com.kmoiseev.demo.springserver.view.EmployeeOutputViewTestValidator.assertEmployeeViewIsCorrect;
import static com.kmoiseev.demo.springserver.view.ErrorViewTestValidator.assertErrorViewIsCorrect;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class SpringServerSaveIntegrationTest extends SpringServerIntegrationTestBase {

  @Autowired
  public SpringServerSaveIntegrationTest(
      EmployeeRepository employeeRepository, TestRestTemplate testRestTemplate) {
    super(employeeRepository, testRestTemplate);
  }

  @Test
  void testCreateEmployeeSuccess() {
    String name = "Petr Fedorov";
    Long salary = 26661L;

    ResponseEntity<EmployeeOutputView> response =
        restTemplateWithTestAuth()
            .postForEntity(
                getBaseUrl() + "employee/create",
                new EmployeeInputView(name, salary),
                EmployeeOutputView.class);

    assertStatusCodeEquals(response, HttpStatus.CREATED);
    assertResponseBodyIsNotEmpty(response);
    assertEmployeeViewIsCorrect(response.getBody(), name, salary);

    Employee savedEmployee = repositoryTestWrapper.getFirstRepoEmployeeOrThrow();
    assertEmployeeIsCorrect(savedEmployee, name, salary);
  }

  @Test
  void testCreateEmployeeReturnsNameCannotBeEmptyWhenEmpty() throws Exception {
    String name = "";
    Long salary = 26661L;

    ResponseEntity<ErrorView> response =
        restTemplateWithTestAuth()
            .postForEntity(
                getBaseUrl() + "employee/create",
                new EmployeeInputView(name, salary),
                ErrorView.class);

    assertStatusCodeEquals(response, BAD_REQUEST);
    assertResponseBodyIsNotEmpty(response);
    assertErrorViewIsCorrect(response.getBody(), "Employee name cannot be empty");

    repositoryTestWrapper.assertRepoIsEmpty();
  }

  @Test
  void testCreateEmployeeReturnsNameCannotBeEmptyWhenNull() throws Exception {
    String name = null;
    Long salary = 2141L;

    ResponseEntity<ErrorView> response =
        restTemplateWithTestAuth()
            .postForEntity(
                getBaseUrl() + "employee/create",
                new EmployeeInputView(name, salary),
                ErrorView.class);

    assertStatusCodeEquals(response, BAD_REQUEST);
    assertResponseBodyIsNotEmpty(response);
    assertErrorViewIsCorrect(response.getBody(), "Employee name cannot be empty");

    repositoryTestWrapper.assertRepoIsEmpty();
  }

  @Test
  void testCreateEmployeeReturnsSalaryCannotBeEmptyWhenNull() throws Exception {
    String name = "valid";
    Long salary = null;

    ResponseEntity<ErrorView> response =
        restTemplateWithTestAuth()
            .postForEntity(
                getBaseUrl() + "employee/create",
                new EmployeeInputView(name, salary),
                ErrorView.class);

    assertStatusCodeEquals(response, BAD_REQUEST);
    assertResponseBodyIsNotEmpty(response);
    assertErrorViewIsCorrect(response.getBody(), "Salary cannot be null");

    repositoryTestWrapper.assertRepoIsEmpty();
  }

  @Test
  void testCreateEmployeeReturnsSalaryCannotBeNegative() throws Exception {
    String name = "valid";
    Long salary = -123L;

    ResponseEntity<ErrorView> response =
        restTemplateWithTestAuth()
            .postForEntity(
                getBaseUrl() + "employee/create",
                new EmployeeInputView(name, salary),
                ErrorView.class);

    assertStatusCodeEquals(response, BAD_REQUEST);
    assertResponseBodyIsNotEmpty(response);
    assertErrorViewIsCorrect(response.getBody(), "Salary cannot be negative");

    repositoryTestWrapper.assertRepoIsEmpty();
  }
}
