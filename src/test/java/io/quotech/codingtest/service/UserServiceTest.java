package io.quotech.codingtest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.quotech.codingtest.domain.EntityId;
import io.quotech.codingtest.model.UserMetadata;
import io.quotech.codingtest.exception.UserAlreadyExistsException;
import io.quotech.codingtest.model.UserRole;
import io.quotech.codingtest.exception.UserNotFoundException;
import io.quotech.codingtest.model.User;
import io.quotech.codingtest.model.mapper.UserMapper;
import io.quotech.codingtest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  public void testGetAll() {
    io.quotech.codingtest.domain.User user1 = mockDomainUser(
      "client1", "user1", "Bob", "Doe", "bob.doe@quotech.io",
      "quotech-test", "Quotech", "Test Manager", io.quotech.codingtest.domain.UserRole.internal);
    io.quotech.codingtest.domain.User user2 = mockDomainUser(
      "client1", "user2", "John", "Smith", "john.smith@quotech.io",
      "quotech-test", "Quotech", "Test Leader", io.quotech.codingtest.domain.UserRole.broker);
    when(userRepository.findAll()).thenReturn(List.of(user1, user2));

    List<User> result = userService.getAllUsers("client1");

    assertThat(result, hasSize(2));
    assertThat(result.get(0).getUserId(), is("user1"));
    assertThat(result.get(0).getMetadata().getFirstName(), is("Bob"));
    assertThat(result.get(0).getMetadata().getLastName(), is("Doe"));
  }

  @Test
  public void testGetUser() throws UserNotFoundException {
    io.quotech.codingtest.domain.User user1 = mockDomainUser(
      "client1", "user1", "Bob", "Doe", "bob.doe@quotech.io",
      "quotech-test", "Quotech", "Test Manager", io.quotech.codingtest.domain.UserRole.internal);
    EntityId id = EntityId.builder().withClientId("client1").withId("user1").build();
    when(userRepository.findById(id)).thenReturn(Optional.of(user1));

    User result = userService.getUser("client1", "user1");

    assertThat(result.getUserId(), is("user1"));
    assertThat(result.getMetadata().getFirstName(), is("Bob"));
    assertThat(result.getMetadata().getLastName(), is("Doe"));
  }

  @Test
  public void testCreate() throws UserAlreadyExistsException {
    User modelUser = mockModelUser(
      "user1", "Bob", "Doe", "bob.doe@quotech.io",
      "quotech-test", "Quotech", "Test Manager", UserRole.internal);
    io.quotech.codingtest.domain.User domainUser = mockDomainUser(
      "client1", "user1", "Bob", "Doe", "bob.doe@quotech.io",
      "quotech-test", "Quotech", "Test Manager", io.quotech.codingtest.domain.UserRole.internal);
    EntityId id = EntityId.builder().withClientId("client1").withId("user1").build();
    when(userRepository.save(domainUser)).thenReturn(domainUser);

    User result = userService.createUser("client1", modelUser);

    assertThat(result.getUserId(), is(modelUser.getUserId()));
    assertThat(result.getMetadata().getFirstName(), is(modelUser.getMetadata().getFirstName()));
    assertThat(result.getMetadata().getLastName(), is(modelUser.getMetadata().getLastName()));
    assertThat(result.getMetadata().getEmailAddress(), is(modelUser.getMetadata().getEmailAddress()));
    assertThat(result.getMetadata().getCompanyId(), is(modelUser.getMetadata().getCompanyId()));
    assertThat(result.getMetadata().getCompanyName(), is(modelUser.getMetadata().getCompanyName()));
    assertThat(result.getMetadata().getJobTitle(), is(modelUser.getMetadata().getJobTitle()));
    assertThat(result.getMetadata().getRole(), is(modelUser.getMetadata().getRole()));
  }

  @Test
  public void testDeleteUser() throws UserNotFoundException {
    io.quotech.codingtest.domain.User user1 = mockDomainUser(
            "client1", "user1", "Bob", "Doe", "bob.doe@quotech.io",
            "quotech-test", "Quotech", "Test Manager", io.quotech.codingtest.domain.UserRole.internal);
    EntityId id = EntityId.builder().withClientId("client1").withId("user1").build();
    when(userRepository.findById(id)).thenReturn(Optional.of(user1));

    User result = userService.deleteUser("client1", "user1");

    assertThat(result.getUserId(), is("user1"));
    assertThat(result.getMetadata().getFirstName(), is("Bob"));
    assertThat(result.getMetadata().getLastName(), is("Doe"));
  }

  private io.quotech.codingtest.domain.User mockDomainUser(
      String clientId, String userId, String firstName, String lastName, String emailAddress,
      String companyId, String companyName, String jobTitle, io.quotech.codingtest.domain.UserRole role) {
    return io.quotech.codingtest.domain.User.builder()
      .withId(EntityId.builder()
        .withClientId(clientId)
        .withId(userId)
        .build())
      .withMetadata(io.quotech.codingtest.domain.UserMetadata.builder()
        .withFirstName(firstName)
        .withLastName(lastName)
        .withEmailAddress(emailAddress)
        .withCompanyId(companyId)
        .withCompanyName(companyName)
        .withJobTitle(jobTitle)
        .withRole(role)
        .build())
      .build();
  }

  private User mockModelUser(
      String userId, String firstName, String lastName, String emailAddress,
      String companyId, String companyName, String jobTitle, UserRole role) {
    return User.builder()
      .withUserId(userId)
      .withMetadata(UserMetadata.builder()
        .withFirstName(firstName)
        .withLastName(lastName)
        .withEmailAddress(emailAddress)
        .withCompanyId(companyId)
        .withCompanyName(companyName)
        .withJobTitle(jobTitle)
        .withRole(role)
        .build())
      .build();
  }
}
