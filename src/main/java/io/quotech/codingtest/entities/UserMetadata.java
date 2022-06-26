package io.quotech.codingtest.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonDeserialize (builder = UserMetadata.Builder.class)
public class UserMetadata {

  private String firstName;
  private String lastName;
  private String emailAddress;
  private String companyId;
  private String officeId;

  public UserMetadata() {
  }

  public UserMetadata(String firstName,
                      String lastName,
                      String emailAddress,
                      String companyId,
                      String officeId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
    this.companyId = companyId;
    this.officeId = officeId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getCompanyId() {
    return companyId;
  }

  public String getOfficeId() {
    return officeId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserMetadata that = (UserMetadata) o;
    return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(emailAddress, that.emailAddress) && Objects.equals(companyId, that.companyId) && Objects.equals(officeId, that.officeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, emailAddress, companyId, officeId);
  }

  @Override
  public String toString() {
    return "UserMetadata{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", emailAddress='" + emailAddress + '\'' +
            ", companyId='" + companyId + '\'' +
            ", officeId='" + officeId + '\'' +
            '}';
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String companyId;
    private String officeId;

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

    public Builder withOfficeId(String officeId) {
      this.officeId = officeId;
      return this;
    }

    public UserMetadata build() {
      return new UserMetadata(firstName, lastName, emailAddress, companyId, officeId);
    }
  }
}
