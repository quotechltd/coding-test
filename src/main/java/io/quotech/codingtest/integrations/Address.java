package io.quotech.codingtest.integrations;

import java.util.Objects;

public class Address {

    private final String firstLine;
    private final String secondLine;
    private final String city;
    private final String postcode;

    private Address(String firstLine, String secondLine, String city, String postcode) {
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.city = city;
        this.postcode = postcode;
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
        Address address = (Address) o;
        return Objects.equals(firstLine, address.firstLine) && Objects.equals(secondLine, address.secondLine) && Objects.equals(city, address.city) && Objects.equals(postcode, address.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstLine, secondLine, city, postcode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "firstLine='" + firstLine + '\'' +
                ", secondLine='" + secondLine + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String firstLine;
        private String secondLine;
        private String city;
        private String postcode;

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

        public Address build() {
            return new Address(firstLine, secondLine, city, postcode);
        }
    }
}
