package io.quotech.codingtest.resource;

import io.quotech.codingtest.exception.UserAlreadyExistsException;
import io.quotech.codingtest.exception.UserNotFoundException;
import io.quotech.codingtest.exception.UserUpdateException;
import io.quotech.codingtest.model.User;
import io.quotech.codingtest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserResource {

  private final UserService userService;

  public UserResource(UserService userService) {
    this.userService = userService;
  }

  @GetMapping ("/clients/{clientId}/users")
  public ResponseEntity<List<User>> list(
      @PathVariable final String clientId) {
    return ResponseEntity.ok(
      userService.getAllUsers(clientId));
  }

  @GetMapping ("/clients/{clientId}/users/{userId}")
  public ResponseEntity<User> get(
      @PathVariable final String clientId,
      @PathVariable final String userId)
    throws UserNotFoundException {
    return ResponseEntity.ok(userService.getUser(clientId, userId));
  }

  @PostMapping ("/clients/{clientId}/users")
  public ResponseEntity<User> create(
      @PathVariable final String clientId,
      @RequestBody final User user)
      throws UserAlreadyExistsException {
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(userService.createUser(clientId, user));
  }

  @PutMapping ("/clients/{clientId}/users/{userId}")
  public ResponseEntity<User> update(
      @PathVariable final String clientId,
      @PathVariable final String userId,
      @RequestBody final User user)
      throws UserNotFoundException, UserUpdateException {
    return ResponseEntity.ok(userService.updateUser(clientId, userId, user));
  }

  @DeleteMapping("/clients/{clientId}/users/{userId}")
  public ResponseEntity<Void> delete(
          @PathVariable final String clientId,
          @PathVariable final String userId)
          throws UserNotFoundException {
    userService.deleteUser(clientId, userId);
    return ResponseEntity.ok().build();
  }
}
