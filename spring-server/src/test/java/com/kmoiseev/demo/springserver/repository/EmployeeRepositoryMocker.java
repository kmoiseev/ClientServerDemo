package com.kmoiseev.demo.springserver.repository;

import com.kmoiseev.demo.springserver.model.Employee;
import lombok.AllArgsConstructor;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@AllArgsConstructor
public class EmployeeRepositoryMocker {
    private final EmployeeRepository repository;

    public void mockFindByIdReturnsEmptyOptional(Integer id) {
        when(repository.findById(eq(id))).thenReturn(Optional.empty());
    }

    public void mockFindByIdReturns(Integer id, Employee employee) {
        when(repository.findById(eq(id))).thenReturn(Optional.of(employee));
    }

    public void mockSavePassThroughEmployee() {
        doAnswer(invocation -> invocation.getArgument(0)).when(repository).save(any());
    }

    public void mockGetAllReturns(Iterable<Employee> employees) {
        when(repository.findAll()).thenReturn(employees);
    }

    public void verifySaveCalledOnceWith(Employee employee) {
        verify(repository, times(1)).save(eq(employee));
    }

    public void verifyDeleteAllCalledOnce() {
        verify(repository, times(1)).deleteAll();
    }

    public Employee captureOnceSavedEmployee() {
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(repository, times(1)).save(employeeArgumentCaptor.capture());
        return employeeArgumentCaptor.getValue();
    }
}
