package io.quotech.codingtest.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;
import java.util.StringJoiner;

@JsonDeserialize (builder = EntityId.Builder.class)
public class EntityId {
  private String organisationId;
  private String id;

  public EntityId() {
  }

  private EntityId(Builder builder) {
    this.organisationId = builder.organisationId;
    this.id = builder.id;
  }

  public String getOrganisationId() {
    return organisationId;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EntityId entityId = (EntityId) o;
    return Objects.equals(organisationId, entityId.organisationId) && Objects.equals(id, entityId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(organisationId, id);
  }

  @Override
  public String toString() {
    return "EntityId{" +
            "organisationId='" + organisationId + '\'' +
            ", id='" + id + '\'' +
            '}';
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String organisationId;
    private String id;

    public Builder withOrganisationId(String organisationId) {
      this.organisationId = organisationId;
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
