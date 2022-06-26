package io.quotech.codingtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {

  private static final long serialVersionUID = 2532865606236881125L;

  public UserNotFoundException () {

  }

  public UserNotFoundException(
      String userId) {
    super(String.format("User %s not found", userId));
  }
}
