package com.kmoiseev.demo.springserver;

import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.model.EmployeeTestValidator;
import com.kmoiseev.demo.springserver.repository.EmployeeRepository;
import com.kmoiseev.demo.springserver.view.output.EmployeeOutputView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.kmoiseev.demo.springserver.ResponseValidator.assertResponseBodyIsNotEmpty;
import static com.kmoiseev.demo.springserver.ResponseValidator.assertStatusCodeEquals;
import static com.kmoiseev.demo.springserver.model.EmployeeTestCreator.create;

public class SpringServerGetAllIntegrationTest extends SpringServerIntegrationTestBase {

    @Autowired
    public SpringServerGetAllIntegrationTest(
            EmployeeRepository employeeRepository,
            TestRestTemplate testRestTemplate
    ) {
        super(employeeRepository, testRestTemplate);
    }

    @Test
    void testGetAllEmployeesWithoutAuth() {
        Employee employeeFirst = repositoryTestWrapper.persistEmployee(create("First", 10L));
        Employee employeeSecond = repositoryTestWrapper.persistEmployee(create("Second", 20L));
        Employee employeeThird = repositoryTestWrapper.persistEmployee(create("Third", 30L));

        ResponseEntity<EmployeeOutputView[]> response = testRestTemplate
                .getForEntity(getBaseUrl() + "employee/get/all/sort/id/asc", EmployeeOutputView[].class);

        assertStatusCodeEquals(response, HttpStatus.OK);
        assertResponseBodyIsNotEmpty(response);

        EmployeeOutputView[] employees = response.getBody();
        EmployeeTestValidator.assertEmployeeIsCorrect(employeeFirst, employees[0]);
        EmployeeTestValidator.assertEmployeeIsCorrect(employeeSecond, employees[1]);
        EmployeeTestValidator.assertEmployeeIsCorrect(employeeThird, employees[2]);
    }

    @Test
    void testGetAllEmployeesWitAuth() {
        Employee employeeFirst = repositoryTestWrapper.persistEmployee(create("First", 10L));
        Employee employeeSecond = repositoryTestWrapper.persistEmployee(create("Second", 20L));
        Employee employeeThird = repositoryTestWrapper.persistEmployee(create("Third", 30L));

        ResponseEntity<EmployeeOutputView[]> response = restTemplateWithTestAuth()
                .getForEntity(getBaseUrl() + "employee/get/all/sort/id/asc", EmployeeOutputView[].class);

        assertStatusCodeEquals(response, HttpStatus.OK);
        assertResponseBodyIsNotEmpty(response);

        EmployeeOutputView[] employees = response.getBody();
        EmployeeTestValidator.assertEmployeeIsCorrect(employeeFirst, employees[0]);
        EmployeeTestValidator.assertEmployeeIsCorrect(employeeSecond, employees[1]);
        EmployeeTestValidator.assertEmployeeIsCorrect(employeeThird, employees[2]);
    }
}
