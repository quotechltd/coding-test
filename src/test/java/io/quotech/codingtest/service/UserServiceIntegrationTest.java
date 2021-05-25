package io.quotech.codingtest.service;

import io.quotech.codingtest.exception.UserAlreadyExistsException;
import io.quotech.codingtest.exception.UserNotFoundException;
import io.quotech.codingtest.model.User;
import io.quotech.codingtest.model.UserMetadata;
import io.quotech.codingtest.model.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;

    final String client1 = "client1";
    final String client2 = "client2";

    final UserMetadata metadata1 = UserMetadata.builder()
            .withFirstName("Nick")
                    .withLastName("Melis")
                    .withEmailAddress("nick@quotech.io")
                    .withCompanyId("company-id-1")
                    .withCompanyName("Test Company Name")
                    .withJobTitle("CTO")
                    .withRole(UserRole.internal)
                    .build();
    final User user1 = User.builder()
            .withUserId("nick")
            .withMetadata(metadata1)
            .build();

    final UserMetadata metadata2 = UserMetadata.builder()
            .withFirstName("Bob")
            .withLastName("Doe")
            .withEmailAddress("bob@quotech.io")
            .withCompanyId("company-id-2")
            .withCompanyName("est Company Name 2")
            .withJobTitle("Junior Software Engineer")
            .withRole(UserRole.internal)
            .build();
    final User user2 = User.builder()
            .withUserId("bob")
            .withMetadata(metadata2)
            .build();

    @Test
    public void testGetAllUsersReturnsUserByClientId() {
        try {
            assertThat(userService.getAllUsers(client1)).isEmpty();
            assertThat(userService.getAllUsers(client2)).isEmpty();

            // Now we create 2 users with different client ids.
            userService.createUser(client1, user1);
            userService.createUser(client2, user2);

            // Checking we have 2 users but it returns them by clientId
            assertThat(userService.getAllUsers(client1)).hasSize(1);
            assertThat(userService.getAllUsers(client2)).hasSize(1);
        } catch (UserAlreadyExistsException e) {
            Assertions.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            assertThat(userService.getAllUsers(client1)).isEmpty();

            // Now we create 1 user and check it has been added properly. We are not checking the client added, as
            // it must be checked in a different method
            userService.createUser(client1, user1);
            assertThat(userService.getAllUsers(client1)).hasSize(1);

            // We are adding a new user with the same client to check we are deleting the proper client later
            userService.createUser(client1, user2);
            assertThat(userService.getAllUsers(client1)).hasSize(2);

            // Here we delete the required client and ensure it doesn't exist anymore
            userService.deleteUser(client1, user1.getUserId());
            assertThat(userService.getAllUsers(client1)).hasSize(1);
            assertThat(userService.getAllUsers(client1).get(0).getUserId()).isEqualTo(user2.getUserId());
        } catch (UserAlreadyExistsException | UserNotFoundException e) {
            Assertions.assertTrue(false);
            e.printStackTrace();
        }
    }
}
