package com.avaliacao.backend.exception;

public class ArgumentException extends RuntimeException {
  public ArgumentException(String message) {
      super(message);
  }
}
