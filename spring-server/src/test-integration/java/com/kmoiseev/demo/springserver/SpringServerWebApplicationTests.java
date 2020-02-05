package com.kmoiseev.demo.springserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmoiseev.demo.springserver.controller.EmployeeController;
import com.kmoiseev.demo.springserver.repository.EmployeeRepository;
import com.kmoiseev.demo.springserver.view.input.EmployeeInputView;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@ActiveProfiles("test")
public class SpringServerWebApplicationTests {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final EmployeeRepository employeeRepository;

    @BeforeEach
    void cleanUpDb() {
        employeeRepository.deleteAll();
    }

    @WithMockUser(username = "TEST", password = "TEST", roles = {"TEST"})
    @Test
    public void testCreateEmployeeSuccess() throws Exception {
        String name = "Petr Fedorov";
        Long salary = 26661L;

        mockMvc.perform(
                post("/employee/create").contentType(MediaType.APPLICATION_JSON_VALUE).content(
                        objectMapper.writeValueAsBytes(new EmployeeInputView(name, salary))
                )
        ).andExpect(status().is2xxSuccessful());
    }

}
