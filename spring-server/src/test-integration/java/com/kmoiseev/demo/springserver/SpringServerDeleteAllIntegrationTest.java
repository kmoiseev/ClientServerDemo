package com.kmoiseev.demo.springserver;

import com.kmoiseev.demo.springserver.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Collections;

import static com.kmoiseev.demo.springserver.model.EmployeeTestCreator.create;

public class SpringServerDeleteAllIntegrationTest extends SpringServerIntegrationTestBase {

    @Autowired
    public SpringServerDeleteAllIntegrationTest(
            EmployeeRepository employeeRepository,
            TestRestTemplate testRestTemplate
    ) {
        super(employeeRepository, testRestTemplate);
    }

    @Test
    void testDeleteAllEmployees() {
        repositoryTestWrapper.persistEmployee(create("First", 10L));
        repositoryTestWrapper.persistEmployee(create("Second", 20L));
        repositoryTestWrapper.persistEmployee(create("Third", 30L));

        restTemplateWithTestAuth().delete(getBaseUrl() + "employee/delete/all", Collections.emptyMap());

        repositoryTestWrapper.assertRepoIsEmpty();
    }
}
