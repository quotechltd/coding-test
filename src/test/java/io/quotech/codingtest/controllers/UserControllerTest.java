package io.quotech.codingtest.controllers;

import io.quotech.codingtest.exception.UserAlreadyExistsException;
import io.quotech.codingtest.exception.UserNotFoundException;
import io.quotech.codingtest.model.User;
import io.quotech.codingtest.service.ClientIdProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureDataMongo
public class UserControllerTest {

  @MockBean
  private ClientIdProvider clientIdProvider;

  @Autowired
  private UserController userController;

  @Autowired
  private MongoTemplate mongoTemplate;

  @BeforeEach
  void setup() {
    given(clientIdProvider.getClientId()).willReturn("quotech-test");
  }

  @AfterEach
  void teardown() {
    mongoTemplate.getDb().drop();
  }

  @Test
  void givenUser_whenCreate_responseIsCreated() throws UserAlreadyExistsException {
    ResponseEntity<User> result = userController.create(TestUserData.USER1);

    assertEquals(HttpStatus.CREATED, result.getStatusCode());
  }

  @Test
  void givenUserExist_whenGet_responseIsOk() throws UserAlreadyExistsException, UserNotFoundException {
    userController.create(TestUserData.USER2);

    ResponseEntity<User> result = userController.get("user2");

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(TestUserData.USER2, result.getBody());
  }

  @Test
  void givenMultipleUserExist_whenGetAll_onlyClintUsersReturned() throws UserAlreadyExistsException {
    given(clientIdProvider.getClientId())
            .willReturn("quotech-test", "quotech-test", "lloyds-test", "quotech-test");
    userController.create(TestUserData.USER1);
    userController.create(TestUserData.USER2);
    userController.create(TestUserData.USER3);

    ResponseEntity<List<User>> result = userController.getAll();

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(2, result.getBody().size());
  }

}
