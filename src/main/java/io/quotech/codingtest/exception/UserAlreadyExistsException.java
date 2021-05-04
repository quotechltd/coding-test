package io.quotech.codingtest.exception;

public class UserAlreadyExistsException extends Exception {

  private static final long serialVersionUID = -5288891485282797062L;

  public UserAlreadyExistsException() {
  }

  public UserAlreadyExistsException(
      String userId) {
    super(String.format("User %s already exists", userId));
  }
}
