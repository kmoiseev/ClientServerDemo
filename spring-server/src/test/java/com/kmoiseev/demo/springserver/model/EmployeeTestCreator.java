package com.kmoiseev.demo.springserver.model;

import com.kmoiseev.demo.springserver.view.input.EmployeeInputView;

public class EmployeeTestCreator {
  public static Employee create(Integer id, String name, Long salary) {
    return create(name, salary).withId(id);
  }

  public static Employee create(String name, Long salary) {
    return Employee.of(new EmployeeInputView(name, salary));
  }
}
