package io.quotech.codingtest.service;

import io.quotech.codingtest.domain.ActionItem;
import io.quotech.codingtest.domain.EntityId;
import io.quotech.codingtest.exception.UserAlreadyExistsException;
import io.quotech.codingtest.exception.UserNotFoundException;
import io.quotech.codingtest.exception.UserUpdateException;
import io.quotech.codingtest.model.User;
import io.quotech.codingtest.mapper.UserMapper;
import io.quotech.codingtest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers(String clientId) {
    return userRepository.findAll().stream()
      .map(UserMapper::map)
      .collect(Collectors.toList());
  }

  public User getUser(String clientId, String userId) throws UserNotFoundException {
    EntityId id = EntityId.builder()
      .withClientId(clientId)
      .withId(userId)
      .build();
    return userRepository.findById(id)
      .map(UserMapper::map)
      .orElseThrow(UserNotFoundException::new);
  }

  public User createUser(String clientId, User user) throws UserAlreadyExistsException {
    EntityId id = EntityId.builder()
      .withClientId(clientId)
      .withId(user.getUserId())
      .build();

    if (userRepository.existsById(id)) {
      throw new UserAlreadyExistsException();
    }

    io.quotech.codingtest.domain.User domain = UserMapper.map(clientId, user);
    domain.getMetadata().setAdded(ActionItem.builder()
      .withActor("system")
      .withTimestamp(LocalDateTime.now())
      .build());
    return UserMapper.map(userRepository.save(domain));
  }

  public User updateUser(String clientId, String userId, User user) throws UserUpdateException, UserNotFoundException {
    if (!userId.equals(user.getUserId())) {
      throw new UserUpdateException();
    }
    EntityId id = EntityId.builder()
      .withClientId(clientId)
      .withId(user.getUserId())
      .build();
    if (!userRepository.existsById(id)) {
      throw new UserNotFoundException();
    }
    io.quotech.codingtest.domain.User domain = UserMapper.map(clientId, user);
    return UserMapper.map(userRepository.save(domain));
  }
}
