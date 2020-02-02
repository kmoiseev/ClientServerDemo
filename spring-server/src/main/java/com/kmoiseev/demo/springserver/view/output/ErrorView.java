package com.kmoiseev.demo.springserver.view.output;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "of")
public class ErrorView {
    private final String error;
}
