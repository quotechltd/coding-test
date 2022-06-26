package io.quotech.codingtest.controllers;

import io.quotech.codingtest.model.User;
import io.quotech.codingtest.model.UserMetadata;
import io.quotech.codingtest.model.UserRole;

public class TestUserData {
    public static final User USER1 = User.builder()
            .withUserId("user1")
            .withMetadata(UserMetadata.builder()
                    .withFirstName("Brad")
                    .withLastName("Pitt")
                    .withEmailAddress("brad.pitt@quotech.io")
                    .withCompanyId("quotech")
                    .withOfficeId("edinburgh")
                    .build())
            .withRole(UserRole.internal)
            .build();

    public static final User USER2 = User.builder()
            .withUserId("user2")
            .withMetadata(UserMetadata.builder()
                    .withFirstName("John")
                    .withLastName("Smith")
                    .withEmailAddress("john.smith@quotech.io")
                    .withCompanyId("quotech")
                    .withOfficeId("manchester")
                    .build())
            .withRole(UserRole.internal)
            .build();

    public static final User USER3 = User.builder()
            .withUserId("user3")
            .withMetadata(UserMetadata.builder()
                    .withFirstName("Jane")
                    .withLastName("Smith")
                    .withEmailAddress("jane.smith@quotech.io")
                    .withCompanyId("lloyds")
                    .withOfficeId("manchester")
                    .build())
            .withRole(UserRole.internal)
            .build();
}
