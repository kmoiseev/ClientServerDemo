package com.kmoiseev.demo.springserver.model;

import com.kmoiseev.demo.springserver.exception.ModelValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EmployeeSalary {
  private final long amount;

  public static EmployeeSalary of(Long salary) {
    if (Objects.isNull(salary)) {
      throw new ModelValidationException("Salary cannot be null");
    }
    if (salary < 0) {
      throw new ModelValidationException("Salary cannot be negative");
    }
    return new EmployeeSalary(salary);
  }
}
