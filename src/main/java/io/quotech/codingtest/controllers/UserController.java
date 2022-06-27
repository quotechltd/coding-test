package io.quotech.codingtest.controllers;

import io.quotech.codingtest.exception.UserAlreadyExistsException;
import io.quotech.codingtest.exception.UserNotFoundException;
import io.quotech.codingtest.model.AddressLabel;
import io.quotech.codingtest.model.User;
import io.quotech.codingtest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<User> create(
          @RequestBody final User user)
          throws UserAlreadyExistsException {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.createUser(user));
  }

  @GetMapping ("/{userId}")
  public ResponseEntity<User> get(
      @PathVariable final String userId)
    throws UserNotFoundException {
    return ResponseEntity.ok(userService.getUser(userId));
  }

  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    return ResponseEntity.ok(
            userService.getAllUsers());
  }

  @GetMapping ("/address")
  public ResponseEntity<List<AddressLabel>> getAll() {
    return ResponseEntity.ok(
            userService.getAllUsers());
  }

}
