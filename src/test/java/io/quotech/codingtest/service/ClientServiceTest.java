package io.quotech.codingtest.service;

import io.quotech.codingtest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith (MockitoExtension.class)
public class ClientServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private ClientService clientService;

  @Test
  public void testGetAll() {
    io.quotech.codingtest.domain.User user1 = mockUser("client1");
    io.quotech.codingtest.domain.User user2 = mockUser("client2");
    when(userRepository.findAll()).thenReturn(List.of(user1, user2));

    Set<String> result = clientService.getAll();

    assertThat(result, hasSize(2));
    assertThat(result, containsInAnyOrder("client1", "client2"));
  }

  private io.quotech.codingtest.domain.User mockUser(String clientId) {
    io.quotech.codingtest.domain.User user = mock(io.quotech.codingtest.domain.User.class, RETURNS_DEEP_STUBS);
    when(user.getId().getClientId()).thenReturn(clientId);
    return user;
  }
}
