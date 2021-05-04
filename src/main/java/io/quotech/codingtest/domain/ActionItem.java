package io.quotech.codingtest.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@JsonDeserialize (builder = ActionItem.Builder.class)
public class ActionItem {

  @Field ("by")
  private String actor;
  @Field ("on")
  private LocalDateTime timestamp;

  public ActionItem() {
    super();
  }

  private ActionItem(
      Builder builder) {
    this.actor = builder.actor;
    this.timestamp = builder.timestamp;
  }

  public String getActor() {
    return actor;
  }

  public void setActor(String actor) {
    this.actor = actor;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Transient
  public static ActionItem create(String actor, LocalDateTime timestamp) {
    return ActionItem.builder()
      .withActor(actor)
      .withTimestamp(timestamp)
      .build();
  }

  @Transient
  public boolean isEmpty() {
    return (actor == null || actor.isEmpty()) && timestamp == null;
  }
  /**
   * Creates builder to build {@link ActionItem}.
   *
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder to build {@link ActionItem}.
   */
  public static final class Builder {
    private String actor;
    private LocalDateTime timestamp;

    private Builder() {
    }

    public Builder withActor(String actor) {
      this.actor = actor;
      return this;
    }

    public Builder withTimestamp(LocalDateTime timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public ActionItem build() {
      return new ActionItem(this);
    }
  }
}

