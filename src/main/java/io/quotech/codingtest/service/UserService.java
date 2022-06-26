package io.quotech.codingtest.service;

import io.quotech.codingtest.entities.EntityId;
import io.quotech.codingtest.exception.UserAlreadyExistsException;
import io.quotech.codingtest.exception.UserNotFoundException;
import io.quotech.codingtest.model.User;
import io.quotech.codingtest.mapper.UserMapper;
import io.quotech.codingtest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final ClientIdProvider clientIdProvider;

  public UserService(UserRepository userRepository,
                     UserMapper userMapper,
                     ClientIdProvider clientIdProvider) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.clientIdProvider = clientIdProvider;
  }

  public User createUser(User user) throws UserAlreadyExistsException {
    String clientId = clientIdProvider.getClientId();

    EntityId id = EntityId.builder()
            .withClientId(clientId)
            .withId(user.getUserId())
            .build();

    if (userRepository.existsById(id)) {
      throw new UserAlreadyExistsException();
    }

    io.quotech.codingtest.entities.User entity = userMapper.map(clientId, user);
    return userMapper.map(userRepository.save(entity));
  }

  public User getUser(String userId) throws UserNotFoundException {
    String clientId = clientIdProvider.getClientId();

    EntityId id = EntityId.builder()
      .withClientId(clientId)
      .withId(userId)
      .build();

    return userRepository.findById(id)
      .map(userMapper::map)
      .orElseThrow(UserNotFoundException::new);
  }

  public List<User> getAllUsers() {

    return userRepository.findAll()
            .stream()
            .map(userMapper::map)
            .collect(Collectors.toList());
  }
}
