package com.kmoiseev.demo.springserver.controller;

import org.junit.jupiter.api.Test;

public class EmployeeControllerDeleteAllTest extends EmployeeControllerTestBase {

    @Test
    void deleteAllServiceMethodGetsCalled() {
        controller.deleteAll();

        employeeServiceMocker.verifyDeleteAllCalledOnce();
    }

}
