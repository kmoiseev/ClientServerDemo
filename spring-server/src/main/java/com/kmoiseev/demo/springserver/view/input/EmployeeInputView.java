package com.kmoiseev.demo.springserver.view.input;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class EmployeeInputView {
    private final String name;
    private final Integer salary;
}
