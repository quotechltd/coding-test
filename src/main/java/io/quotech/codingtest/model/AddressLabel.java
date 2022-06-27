package io.quotech.codingtest.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonDeserialize(builder = AddressLabel.Builder.class)
public class AddressLabel {

    private String name;
    private String company;
    private String city;

    public AddressLabel(String name, String company, String city) {
        this.name = name;
        this.company = company;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressLabel that = (AddressLabel) o;
        return Objects.equals(name, that.name) && Objects.equals(company, that.company) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, company, city);
    }

    @Override
    public String toString() {
        return "AddressLabel{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public static AddressLabel.Builder builder() {
        return new AddressLabel.Builder();
    }

    public static final class Builder {

        private String name;
        private String company;
        private String city;

        private Builder() {
        }

        public AddressLabel.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public AddressLabel.Builder withCompany(String company) {
            this.company = company;
            return this;
        }

        public AddressLabel.Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressLabel build() {
            return new AddressLabel(name, company, city);
        }
    }

}
