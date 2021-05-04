package io.quotech.codingtest.resource;

import io.quotech.codingtest.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping ("/api/v1")
public class ClientResource {

  private final ClientService clientService;

  public ClientResource(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping ("/clients")
  public ResponseEntity<Set<String>> list() {
    return ResponseEntity.ok(
      clientService.getAll());
  }
}
