package io.quotech.codingtest.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@JsonDeserialize (builder = User.Builder.class)
@Document (collection = "users")
public class User {

  @Id
  private EntityId id;
  private UserMetadata metadata;
  private UserRole role;

  public User() {
  }

  public User(EntityId id,
              UserMetadata metadata,
              UserRole role) {
    this.id = id;
    this.metadata = metadata;
    this.role = role;
  }

  public EntityId getId() {
    return id;
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
    return Objects.equals(id, user.id) && Objects.equals(metadata, user.metadata) && role == user.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, metadata, role);
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", metadata=" + metadata +
            ", role=" + role +
            '}';
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private EntityId id;
    private UserMetadata metadata;
    private UserRole role;

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

    public Builder withRole(UserRole role) {
      this.role = role;
      return this;
    }

    public User build() {
      return new User(id, metadata, role);
    }
  }
}
