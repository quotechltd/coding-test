package io.quotech.codingtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.BAD_REQUEST)
public class UserUpdateException extends Exception {

  public UserUpdateException() {
    super();
  }

  public UserUpdateException(Throwable cause) {
    super(cause);
  }

  public UserUpdateException(String message) {
    super(message);
  }
}
