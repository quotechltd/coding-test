package io.quotech.codingtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize (builder = User.Builder.class)
public class User {

  private final String userId;
  @JsonProperty ("user")
  private final UserMetadata metadata;

  private User(
      Builder builder) {
    this.userId = builder.userId;
    this.metadata = builder.metadata;
  }

  public String getUserId() {
    return userId;
  }

  public UserMetadata getMetadata() {
    return metadata;
  }

  /**
   * Creates builder to build {@link User}.
   *
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(User user) {
    return new Builder(user);
  }

  /**
   * Builder to build {@link User}.
   */
  public static final class Builder {
    private String userId;
    @JsonProperty ("user")
    private UserMetadata metadata;

    private Builder() {
    }

    private Builder(
        User user) {
      this.userId = user.getUserId();
      this.metadata = user.getMetadata();
    }

    public Builder withUserId(String id) {
      this.userId = id;
      return this;
    }

    public Builder withMetadata(UserMetadata metadata) {
      this.metadata = metadata;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
