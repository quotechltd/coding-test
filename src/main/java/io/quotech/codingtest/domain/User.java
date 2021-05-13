package io.quotech.codingtest.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonDeserialize (builder = User.Builder.class)
@Document (collection = "users")
public class User {

  @Id
  private EntityId id;
  @Field ("user")
  private UserMetadata metadata;

  private User(
      Builder builder) {
    this.id = builder.id;
    this.metadata = builder.metadata;
  }

  public User() {
    super();
  }

  public EntityId getId() {
    return id;
  }

  public void setId(EntityId id) {
    this.id = id;
  }

  public UserMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(UserMetadata metadata) {
    this.metadata = metadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(metadata, user.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, metadata);
  }

  /**
   * Creates builder to build {@link User}.
   *
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder to build {@link User}.
   */
  public static final class Builder {
    private EntityId id;
    private UserMetadata metadata;

    private Builder() {
    }

    public Builder withId(EntityId id) {
      this.id = id;
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
