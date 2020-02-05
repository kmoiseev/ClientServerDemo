package com.kmoiseev.demo.springserver.controller;

import com.kmoiseev.demo.springserver.model.Employee;
import com.kmoiseev.demo.springserver.service.EmployeeService;
import com.kmoiseev.demo.springserver.view.input.EmployeeInputView;
import com.kmoiseev.demo.springserver.view.output.EmployeeOutputView;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(
        path = "/employee/",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class EmployeeController extends ControllerBase {

    private final EmployeeService service;

    @PostMapping(
            value = "create",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public EmployeeOutputView create(
            @RequestBody EmployeeInputView employeeInputView
    ) {
        return EmployeeOutputView.of(
                service.create(
                        Employee.of(employeeInputView)
                )
        );
    }

    @PatchMapping(
            value = "update/{employeeId}/salary/{salary}"
    )
    public EmployeeOutputView updateEmployeeSalary(
            @PathVariable("employeeId") Integer employeeId,
            @PathVariable("salary") Long salary
    ) {
        return EmployeeOutputView.of(
                service.updateSalary(employeeId, salary)
        );
    }

    @GetMapping(
            value = "get/all/sort/id/asc"
    )
    public Stream<EmployeeOutputView> getAllSortById() {
        return StreamSupport.stream(
                service.getAll().spliterator(),
                false
        )
                .sorted(Comparator.comparingInt(Employee::getId))
                .map(EmployeeOutputView::of);
    }

    @DeleteMapping(
            value = "delete/all"
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAll() {
        service.deleteAll();
    }
}
