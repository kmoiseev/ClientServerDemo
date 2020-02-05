package com.kmoiseev.demo.springserver.view.output;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "of")
@Getter
public class ErrorView {
    private final String error;
}
