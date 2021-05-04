package io.quotech.codingtest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize (builder = UserMetadata.Builder.class)
public class UserMetadata {

  private final String firstName;
  private final String lastName;
  private final String emailAddress;
  private String companyId;
  private String companyName;
  private String jobTitle;
  private UserRole role;
  private final ActionItem added;

  private UserMetadata(
      Builder builder) {
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.emailAddress = builder.emailAddress;
    this.companyId = builder.companyId;
    this.companyName = builder.companyName;
    this.jobTitle = builder.jobTitle;
    this.role = builder.role;
    this.added = builder.added;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @JsonIgnore
  public String getFullName() {
    return String.format("%s %s", firstName, lastName);
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getCompanyId() {
    return companyId;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public UserRole getRole() {
    return role;
  }

  public ActionItem getAdded() {
    return added;
  }

  /**
   * Creates builder to build {@link UserMetadata}.
   *
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(
      UserMetadata userMetadata) {
    return new Builder(userMetadata);
  }

  /**
   * Builder to build {@link UserMetadata}.
   */
  public static final class Builder {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String companyId;
    private String companyName;
    private String jobTitle;
    private UserRole role;
    private ActionItem added;

    private Builder() {
    }

    private Builder(
        UserMetadata userMetadata) {
      this.firstName = userMetadata.getFirstName();
      this.lastName = userMetadata.getLastName();
      this.emailAddress = userMetadata.getEmailAddress();
      this.companyId = userMetadata.getCompanyId();
      this.companyName = userMetadata.getCompanyName();
      this.jobTitle = userMetadata.getJobTitle();
      this.role = userMetadata.getRole();
      this.added = userMetadata.getAdded();
    }

    public Builder withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder withLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder withEmailAddress(String emailAddress) {
      this.emailAddress = emailAddress;
      return this;
    }

    public Builder withCompanyId(String companyId) {
      this.companyId = companyId;
      return this;
    }

    public Builder withCompanyName(String companyName) {
      this.companyName = companyName;
      return this;
    }

    public Builder withJobTitle(String jobTitle) {
      this.jobTitle = jobTitle;
      return this;
    }

    public Builder withRole(UserRole role) {
      this.role = role;
      return this;
    }

    public Builder withAdded(ActionItem added) {
      this.added = added;
      return this;
    }

    public UserMetadata build() {
      return new UserMetadata(this);
    }
  }
}
