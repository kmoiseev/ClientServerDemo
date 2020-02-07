package com.kmoiseev.demo.springserver.view;

import com.kmoiseev.demo.springserver.view.output.ErrorView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorViewTestValidator {
  public static void assertErrorViewIsCorrect(ErrorView errorView, String message) {
    assertEquals(message, errorView.getError(), "Error message is not correct");
  }
}
