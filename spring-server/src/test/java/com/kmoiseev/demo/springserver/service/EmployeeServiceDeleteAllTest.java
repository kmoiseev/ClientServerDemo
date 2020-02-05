package com.kmoiseev.demo.springserver.service;

import org.junit.jupiter.api.Test;

public class EmployeeServiceDeleteAllTest extends EmployeeServiceTestBase {

    @Test
    void deleteAllRepositoryMethodGetsCalledOnce() {
        service.deleteAll();

        repositoryMocker.verifyDeleteAllCalledOnce();
    }

}
