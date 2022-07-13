package io.quotech.codingtest.model;

import java.util.Objects;

public class AddressLabel {

    private final String name;
    private final String company;
    private final String firstLine;
    private final String secondLine;
    private final String city;
    private final String postcode;

    public AddressLabel(String name, String company, String firstLine, String secondLine, String city, String postcode) {
        this.name = name;
        this.company = company;
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.city = city;
        this.postcode = postcode;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressLabel that = (AddressLabel) o;
        return Objects.equals(name, that.name) && Objects.equals(company, that.company) && Objects.equals(firstLine, that.firstLine) && Objects.equals(secondLine, that.secondLine) && Objects.equals(city, that.city) && Objects.equals(postcode, that.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, company, firstLine, secondLine, city, postcode);
    }

    @Override
    public String toString() {
        return "AddressLabel{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", firstLine='" + firstLine + '\'' +
                ", secondLine='" + secondLine + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String company;
        private String firstLine;
        private String secondLine;
        private String city;
        private String postcode;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCompany(String company) {
            this.company = company;
            return this;
        }

        public Builder withFirstLine(String firstLine) {
            this.firstLine = firstLine;
            return this;
        }

        public Builder withSecondLine(String secondLine) {
            this.secondLine = secondLine;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public AddressLabel build() {
            return new AddressLabel(name, company, firstLine, secondLine, city, postcode);
        }
    }
}
