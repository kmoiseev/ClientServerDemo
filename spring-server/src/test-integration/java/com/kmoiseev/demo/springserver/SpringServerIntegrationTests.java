package com.kmoiseev.demo.springserver;

import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.model.EmployeeTestCreator;
import com.kmoiseev.demo.springserver.repository.EmployeeRepository;
import com.kmoiseev.demo.springserver.view.input.EmployeeInputView;
import com.kmoiseev.demo.springserver.view.output.EmployeeOutputView;
import com.kmoiseev.demo.springserver.view.output.ErrorView;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static com.kmoiseev.demo.springserver.ResponseValidator.assertResponseBodyIsNotEmpty;
import static com.kmoiseev.demo.springserver.ResponseValidator.assertStatusCodeEquals;
import static com.kmoiseev.demo.springserver.model.EmployeeTestValidator.assertEmployeeIsCorrect;
import static com.kmoiseev.demo.springserver.view.EmployeeOutputViewTestValidator.assertEmployeeViewIsCorrect;
import static com.kmoiseev.demo.springserver.view.ErrorViewTestValidator.assertErrorViewIsCorrect;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SpringServerIntegrationTests {

    private final EmployeeRepositoryTestWrapper repositoryTestWrapper;
    private TestRestTemplate testRestTemplate;

    @Autowired
    public SpringServerIntegrationTests(
            EmployeeRepository employeeRepository,
            TestRestTemplate testRestTemplate
    ) {
        this.repositoryTestWrapper = new EmployeeRepositoryTestWrapper(employeeRepository);
        this.testRestTemplate = testRestTemplate;

        // Default RestTemplate requestFactory does not support PATCH
        // Using apache Http request factory
        RestTemplate restTemplate = testRestTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

    @LocalServerPort
    private int port;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/";
    }

    @BeforeEach
    void cleanUpDb() {
        repositoryTestWrapper.clean();
    }

    private TestRestTemplate restTemplateWithTestAuth() {
        return testRestTemplate.withBasicAuth("TEST", "TEST");
    }

    @Test
    void testCreateEmployeeSuccess() {
        String name = "Petr Fedorov";
        Long salary = 26661L;

        ResponseEntity<EmployeeOutputView> response = restTemplateWithTestAuth()
                .postForEntity(
                        getBaseUrl() + "employee/create",
                        new EmployeeInputView(name, salary),
                        EmployeeOutputView.class
                );

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

        ResponseEntity<ErrorView> response = restTemplateWithTestAuth()
                .postForEntity(
                        getBaseUrl() + "employee/create",
                        new EmployeeInputView(name, salary),
                        ErrorView.class
                );

        assertStatusCodeEquals(response, BAD_REQUEST);
        assertResponseBodyIsNotEmpty(response);
        assertErrorViewIsCorrect(response.getBody(), "Employee name cannot be empty");

        repositoryTestWrapper.assertRepoIsEmpty();
    }

    @Test
    void testCreateEmployeeReturnsNameCannotBeEmptyWhenNull() throws Exception {
        String name = null;
        Long salary = 2141L;

        ResponseEntity<ErrorView> response = restTemplateWithTestAuth()
                .postForEntity(
                        getBaseUrl() + "employee/create",
                        new EmployeeInputView(name, salary),
                        ErrorView.class
                );

        assertStatusCodeEquals(response, BAD_REQUEST);
        assertResponseBodyIsNotEmpty(response);
        assertErrorViewIsCorrect(response.getBody(), "Employee name cannot be empty");

        repositoryTestWrapper.assertRepoIsEmpty();
    }

    @Test
    void testCreateEmployeeReturnsSalaryCannotBeEmptyWhenNull() throws Exception {
        String name = "valid";
        Long salary = null;

        ResponseEntity<ErrorView> response = restTemplateWithTestAuth()
                .postForEntity(
                        getBaseUrl() + "employee/create",
                        new EmployeeInputView(name, salary),
                        ErrorView.class
                );

        assertStatusCodeEquals(response, BAD_REQUEST);
        assertResponseBodyIsNotEmpty(response);
        assertErrorViewIsCorrect(response.getBody(), "Salary cannot be null");

        repositoryTestWrapper.assertRepoIsEmpty();
    }

    @Test
    void testCreateEmployeeReturnsSalaryCannotBeNegative() throws Exception {
        String name = "valid";
        Long salary = -123L;

        ResponseEntity<ErrorView> response = restTemplateWithTestAuth()
                .postForEntity(
                        getBaseUrl() + "employee/create",
                        new EmployeeInputView(name, salary),
                        ErrorView.class
                );

        assertStatusCodeEquals(response, BAD_REQUEST);
        assertResponseBodyIsNotEmpty(response);
        assertErrorViewIsCorrect(response.getBody(), "Salary cannot be negative");

        repositoryTestWrapper.assertRepoIsEmpty();
    }

    @Test
    void testUpdateEmployeeSuccess() {
        String name = "Vlad Testov";
        Long salary = 26661L;
        Integer id = repositoryTestWrapper.persistEmployee(EmployeeTestCreator.create(name, salary));

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
        Integer id = repositoryTestWrapper.persistEmployee(EmployeeTestCreator.create(name, salaryOld));

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

        repositoryTestWrapper.assertRepoIsEmpty();
    }
}
