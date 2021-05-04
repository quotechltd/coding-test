package io.quotech.codingtest.resource;

import io.quotech.codingtest.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith (MockitoExtension.class)
public class ClientResourceTest {

  @Mock
  private ClientService clientService;

  @InjectMocks
  private ClientResource clientResource;

  @Test
  public void whenGettingAllClients_willReturnListOfClientNames() {
    when(clientService.getAll()).thenReturn(Set.of("client1", "client2"));

    ResponseEntity<Set<String>> result = clientResource.list();

    assertThat(result.getStatusCode(), is(HttpStatus.OK));
    assertThat(result.getBody(), containsInAnyOrder("client1", "client2"));
  }
}
