package io.quotech.codingtest.resource;

import io.quotech.codingtest.exception.UserAlreadyExistsException;
import io.quotech.codingtest.exception.UserNotFoundException;
import io.quotech.codingtest.exception.UserUpdateException;
import io.quotech.codingtest.model.User;
import io.quotech.codingtest.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserResourceTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserResource userResource;

  @Test
  public void whenGettingAllUsers_willReturnUsers() {
    User user1 = mock(User.class);
    User user2 = mock(User.class);
    when(userService.getAllUsers("client1")).thenReturn(List.of(user1, user2));

    ResponseEntity<List<User>> result = userResource.list("client1");
    assertThat(result.getStatusCode(), is(HttpStatus.OK));
    assertThat(result.getBody(), containsInAnyOrder(user1, user2));
  }

  @Test
  public void whenGettingUserById_willReturnUser() throws UserNotFoundException {
    User user1 = mock(User.class);
    when(userService.getUser("client1", "user1")).thenReturn(user1);

    ResponseEntity<User> result = userResource.get("client1", "user1");

    assertThat(result.getStatusCode(), is(HttpStatus.OK));
    assertThat(result.getBody(), is(user1));
  }

  @Test
  public void whenGettingNonExistingUser_willThrowException() throws UserNotFoundException {
    when(userService.getUser("client2", "user2")).thenThrow(new UserNotFoundException());

    assertThrows(UserNotFoundException.class, () -> userResource.get("client2", "user2"));
  }

  @Test
  public void whenCreatingUser_willReturnNewlyCreatedUser() throws UserAlreadyExistsException {
    User user1 = mock(User.class);
    when(userService.createUser("client1", user1)).thenReturn(user1);

    ResponseEntity<User> result = userResource.create("client1", user1);

    assertThat(result.getStatusCode(), is(HttpStatus.CREATED));
    assertThat(result.getBody(), is(user1));
  }

  @Test
  public void whenCreatingAlreadyExistingUser_willThrowException() throws UserAlreadyExistsException {
    User user1 = mock(User.class);
    when(userService.createUser("client1", user1)).thenThrow(new UserAlreadyExistsException());

    assertThrows(UserAlreadyExistsException.class, () -> userResource.create("client1", user1));
  }

  @Test
  public void whenUpdatingUser_willReturnUpdatedUser() throws UserNotFoundException, UserUpdateException {
    User user1 = mock(User.class);
    when(userService.updateUser("client1", "user1", user1)).thenReturn(user1);

    ResponseEntity<User> result = userResource.update("client1", "user1", user1);

    assertThat(result.getStatusCode(), is(HttpStatus.OK));
    assertThat(result.getBody(), is(user1));
  }

  @Test
  public void whenUpdatingNonExistingUser_willThrowException() throws UserNotFoundException, UserUpdateException {
    User user1 = mock(User.class);
    when(userService.updateUser("client1", "user1", user1)).thenThrow(new UserNotFoundException());

    assertThrows(UserNotFoundException.class, () -> userResource.update("client1", "user1", user1));

  }

  @Test
  public void whenDeletingUser_willReturnNewlyCreatedUser() throws UserNotFoundException {
    User user1 = mock(User.class);
    when(userService.deleteUser("client1", "user1")).thenReturn(user1);

    ResponseEntity<User> result = userResource.delete("client1", "user1");

    assertThat(result.getStatusCode(), is(HttpStatus.ACCEPTED));
    assertThat(result.getBody(), is(user1));
  }

  @Test
  public void whenDeletingNotExistingUser_willThrowException() throws UserNotFoundException {
    User user1 = mock(User.class);
    when(userService.deleteUser("client1", "user1")).thenThrow(new UserNotFoundException());

    assertThrows(UserNotFoundException.class, () -> userResource.delete("client1", "user1"));
  }
}
