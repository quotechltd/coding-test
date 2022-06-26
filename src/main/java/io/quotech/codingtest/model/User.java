package io.quotech.codingtest.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonDeserialize (builder = User.Builder.class)
public class User {

  private final String userId;
  private final UserMetadata metadata;
  private final UserRole role;

  public User(String userId, UserMetadata metadata, UserRole role) {
    this.userId = userId;
    this.metadata = metadata;
    this.role = role;
  }

  public String getUserId() {
    return userId;
  }

  public UserMetadata getMetadata() {
    return metadata;
  }

  public UserRole getRole() {
    return role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(userId, user.userId) && Objects.equals(metadata, user.metadata) && role == user.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, metadata, role);
  }

  @Override
  public String toString() {
    return "User{" +
            "userId='" + userId + '\'' +
            ", metadata=" + metadata +
            ", role=" + role +
            '}';
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String userId;
    private UserMetadata metadata;
    private UserRole role;

    private Builder() {
    }

    public Builder withUserId(String userId) {
      this.userId = userId;
      return this;
    }

    public Builder withMetadata(UserMetadata metadata) {
      this.metadata = metadata;
      return this;
    }

    public Builder withRole(UserRole role) {
      this.role = role;
      return this;
    }

    public User build() {
      return new User(userId, metadata, role);
    }
  }
}
