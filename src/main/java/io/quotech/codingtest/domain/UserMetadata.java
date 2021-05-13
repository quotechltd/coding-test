package io.quotech.codingtest.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonDeserialize (builder = UserMetadata.Builder.class)
public class UserMetadata {

  private String firstName;
  private String lastName;
  private String emailAddress;
  private String companyId;
  private String companyName;
  private String jobTitle;
  private UserRole role;
  private ActionItem added;

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

  public UserMetadata() {
    super();
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public ActionItem getAdded() {
    return added;
  }

  public void setAdded(ActionItem added) {
    this.added = added;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserMetadata that = (UserMetadata) o;
    return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName)
      && Objects.equals(emailAddress, that.emailAddress) && Objects.equals(companyId, that.companyId)
      && Objects.equals(companyName, that.companyName) && Objects.equals(jobTitle, that.jobTitle)
      && role == that.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, emailAddress, companyId, companyName, jobTitle, role);
  }

  /**
   * Creates builder to build {@link UserMetadata}.
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
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
