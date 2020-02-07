package com.kmoiseev.demo.springserver.exception;

import lombok.Getter;

@Getter
public class ModelValidationException extends RuntimeException {
  public ModelValidationException(String message) {
    super(message);
  }
}
