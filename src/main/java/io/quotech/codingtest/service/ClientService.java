package io.quotech.codingtest.service;

import io.quotech.codingtest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientService {

  private final UserRepository userRepository;

  public ClientService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Set<String> getAll() {
    return userRepository.findAll().stream()
      .map(user -> user.getId().getClientId())
      .collect(Collectors.toSet());
  }
}
