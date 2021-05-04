package io.quotech.codingtest.model.mapper;

import io.quotech.codingtest.model.ActionItem;
import io.quotech.codingtest.model.User;
import io.quotech.codingtest.model.UserMetadata;
import io.quotech.codingtest.model.UserRole;

public class UserMapper {

  public static User map(io.quotech.codingtest.domain.User domain) {
    User.Builder builder = User.builder();
    builder.withUserId(domain.getId().getId());
    builder.withMetadata(map(domain.getMetadata()));
    return builder.build();
  }

  public static io.quotech.codingtest.domain.User map(String clientId, User model) {
    io.quotech.codingtest.domain.User.Builder builder = io.quotech.codingtest.domain.User.builder();
    builder.withId(io.quotech.codingtest.domain.EntityId.builder()
      .withClientId(clientId)
      .withId(model.getUserId())
      .build());
    builder.withMetadata(map(model.getMetadata()));
    return builder.build();
  }

  public static UserMetadata map(io.quotech.codingtest.domain.UserMetadata domain) {
    if (domain == null) {
      return null;
    }
    UserMetadata.Builder builder = UserMetadata.builder();
    builder.withFirstName(domain.getFirstName());
    builder.withLastName(domain.getLastName());
    builder.withEmailAddress(domain.getEmailAddress());
    builder.withCompanyId(domain.getCompanyId());
    builder.withCompanyName(domain.getCompanyName());
    builder.withJobTitle(domain.getJobTitle());
    builder.withRole(map(domain.getRole()));
    builder.withAdded(map(domain.getAdded()));
    return builder.build();
  }

  public static io.quotech.codingtest.domain.UserMetadata map(UserMetadata model) {
    if (model == null) {
      return null;
    }
    return io.quotech.codingtest.domain.UserMetadata.builder()
      .withFirstName(model.getFirstName())
      .withLastName(model.getLastName())
      .withEmailAddress(model.getEmailAddress())
      .withCompanyId(model.getCompanyId())
      .withCompanyName(model.getCompanyName())
      .withJobTitle(model.getJobTitle())
      .withRole(map(model.getRole()))
      .withAdded(map(model.getAdded()))
      .build();
  }

  public static io.quotech.codingtest.domain.UserRole map(UserRole model) {
    switch (model) {
      case internal:
        return io.quotech.codingtest.domain.UserRole.internal;
      case broker:
        return io.quotech.codingtest.domain.UserRole.broker;
      case reinsurer:
        return io.quotech.codingtest.domain.UserRole.reinsurer;
      default:
        throw new UnsupportedOperationException("UserRole not supported: " + model);
    }
  }

  public static UserRole map(io.quotech.codingtest.domain.UserRole domain) {
    switch (domain) {
      case internal:
        return UserRole.internal;
      case broker:
        return UserRole.broker;
      case reinsurer:
        return UserRole.reinsurer;
      default:
        throw new UnsupportedOperationException("UserRole not supported: " + domain);
    }
  }

  private static ActionItem map(io.quotech.codingtest.domain.ActionItem domain) {
    if (domain == null) {
      return null;
    }
    return ActionItem.builder()
      .withActor(domain.getActor())
      .withTimestamp(domain.getTimestamp())
      .build();
  }

  private static io.quotech.codingtest.domain.ActionItem map(ActionItem model) {
    if (model == null) {
      return null;
    }
    return io.quotech.codingtest.domain.ActionItem.builder()
      .withActor(model.getActor())
      .withTimestamp(model.getTimestamp())
      .build();
  }
}
