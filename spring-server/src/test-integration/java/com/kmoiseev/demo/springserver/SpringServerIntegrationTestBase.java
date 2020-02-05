package com.kmoiseev.demo.springserver;

import com.kmoiseev.demo.springserver.repository.EmployeeRepository;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SpringServerIntegrationTestBase {

    final EmployeeRepositoryTestWrapper repositoryTestWrapper;
    TestRestTemplate testRestTemplate;

    @Autowired
    public SpringServerIntegrationTestBase(
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

    TestRestTemplate restTemplateWithTestAuth() {
        return testRestTemplate.withBasicAuth("TEST", "TEST");
    }

    @LocalServerPort
    private int port;

    String getBaseUrl() {
        return "http://localhost:" + port + "/";
    }

    @BeforeEach
    void cleanUpDb() {
        repositoryTestWrapper.clean();
    }

}
