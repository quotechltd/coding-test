package io.quotech.codingtest.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;
import java.util.StringJoiner;

@JsonDeserialize (builder = EntityId.Builder.class)
public class EntityId {
  private String clientId;
  private String id;

  public EntityId() {
    super();
  }

  private EntityId(Builder builder) {
    this.clientId = builder.clientId;
    this.id = builder.id;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    EntityId entityId = (EntityId) o;
    return Objects.equals(clientId, entityId.clientId) && Objects.equals(id, entityId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, id);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", EntityId.class.getSimpleName() + "[", "]")
        .add("clientId='" + clientId + "'")
        .add("id='" + id + "'")
        .toString();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String clientId;
    private String id;

    public Builder withClientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    public EntityId build() {
      return new EntityId(this);
    }
  }
}
