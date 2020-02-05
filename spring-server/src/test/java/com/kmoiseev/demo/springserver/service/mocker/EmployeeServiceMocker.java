package com.kmoiseev.demo.springserver.service.mocker;

import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.model.EmployeeTestCreator;
import com.kmoiseev.demo.springserver.service.EmployeeService;
import lombok.AllArgsConstructor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AllArgsConstructor
public class EmployeeServiceMocker {

    private final EmployeeService service;

    public void mockCreatePassThroughEmployee() {
        doAnswer(invocation -> invocation.getArgument(0)).when(service).create(any());
    }

    public void mockUpdatePassThroughIdAndSalaryWithName(String name) {
        doAnswer(invocation ->
                EmployeeTestCreator.create(
                        invocation.getArgument(0, Integer.class),
                        name,
                        invocation.getArgument(1, Long.class)
                )
        ).when(service).updateSalary(any(), any());
    }

    public void mockGetAllReturnsEmployees(Iterable<Employee> employees) {
        when(service.getAll()).thenReturn(employees);
    }

    public void verifyDeleteAllCalledOnce() {
        verify(service, times(1)).deleteAll();
    }
}
