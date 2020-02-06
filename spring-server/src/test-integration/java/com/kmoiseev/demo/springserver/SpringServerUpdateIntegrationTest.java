package com.kmoiseev.demo.springserver;

import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.model.EmployeeTestCreator;
import com.kmoiseev.demo.springserver.repository.EmployeeRepository;
import com.kmoiseev.demo.springserver.view.output.EmployeeOutputView;
import com.kmoiseev.demo.springserver.view.output.ErrorView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.kmoiseev.demo.springserver.ResponseValidator.assertResponseBodyIsNotEmpty;
import static com.kmoiseev.demo.springserver.ResponseValidator.assertStatusCodeEquals;
import static com.kmoiseev.demo.springserver.model.EmployeeTestValidator.assertEmployeeIsCorrect;
import static com.kmoiseev.demo.springserver.view.EmployeeOutputViewTestValidator.assertEmployeeViewIsCorrect;
import static com.kmoiseev.demo.springserver.view.ErrorViewTestValidator.assertErrorViewIsCorrect;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class SpringServerUpdateIntegrationTest extends SpringServerIntegrationTestBase {

    @Autowired
    public SpringServerUpdateIntegrationTest(
            EmployeeRepository employeeRepository,
            TestRestTemplate testRestTemplate
    ) {
        super(employeeRepository, testRestTemplate);
    }

    @Test
    void testUpdateEmployeeSuccess() {
        String name = "Vlad Testov";
        Long salary = 26661L;
        Integer id = repositoryTestWrapper.persistEmployee(EmployeeTestCreator.create(name, salary)).getId();

        ResponseEntity<EmployeeOutputView> response = restTemplateWithTestAuth()
                .exchange(
                        getBaseUrl() + "employee/update/" + id + "/salary/" + salary,
                        HttpMethod.PATCH,
                        null,
                        EmployeeOutputView.class
                );

        assertStatusCodeEquals(response, HttpStatus.OK);
        assertResponseBodyIsNotEmpty(response);
        assertEmployeeViewIsCorrect(response.getBody(), name, salary);

        Employee savedEmployee = repositoryTestWrapper.getEmployeeOrThrow(id);
        assertEmployeeIsCorrect(savedEmployee, name, salary);
    }

    @Test
    void testUpdateEmployeeReturnsSalaryCannotBeNegative() {
        String name = "George";
        Long salaryOld = 245L;
        Long salaryNew = -23L;
        Integer id = repositoryTestWrapper.persistEmployee(EmployeeTestCreator.create(name, salaryOld)).getId();

        ResponseEntity<ErrorView> response = restTemplateWithTestAuth()
                .exchange(
                        getBaseUrl() + "employee/update/" + id + "/salary/" + salaryNew,
                        HttpMethod.PATCH,
                        null,
                        ErrorView.class
                );

        assertStatusCodeEquals(response, BAD_REQUEST);
        assertResponseBodyIsNotEmpty(response);
        assertErrorViewIsCorrect(response.getBody(), "Salary cannot be negative");
        Employee employee = repositoryTestWrapper.getEmployeeOrThrow(id);
        assertEmployeeIsCorrect(employee, name, salaryOld);
    }

    @Test
    void testUpdateEmployeeReturnsEmployeeNotFound() {
        String name = "George";
        Long salary = 2145L;
        Integer idValid = repositoryTestWrapper.persistEmployee(EmployeeTestCreator.create(name, salary)).getId();
        Integer idInvalid = idValid + 1;

        ResponseEntity<ErrorView> response = restTemplateWithTestAuth()
                .exchange(
                        getBaseUrl() + "employee/update/" + idInvalid + "/salary/" + salary,
                        HttpMethod.PATCH,
                        null,
                        ErrorView.class
                );

        assertStatusCodeEquals(response, NOT_FOUND);
        assertResponseBodyIsNotEmpty(response);
        assertErrorViewIsCorrect(response.getBody(), "Employee with id " + idInvalid + " does not exist");
    }
}
