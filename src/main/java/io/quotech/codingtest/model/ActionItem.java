package io.quotech.codingtest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

@JsonDeserialize (builder = ActionItem.Builder.class)
@JsonInclude (Include.NON_EMPTY)
public class ActionItem {

  @JsonProperty ("by")
  private final String actor;
  @JsonProperty ("on")
  private final LocalDateTime timestamp;

  private ActionItem(
      Builder builder) {
    this.actor = builder.actor;
    this.timestamp = builder.timestamp;
  }

  public String getActor() {
    return actor;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
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
