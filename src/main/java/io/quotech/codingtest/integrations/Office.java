package io.quotech.codingtest.integrations;

import java.util.Objects;

public class Office {
    private final String officeId;
    private final Address address;

    public Office(String officeId, Address address) {
        this.officeId = officeId;
        this.address = address;
    }

    public String getOfficeId() {
        return officeId;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return Objects.equals(officeId, office.officeId) && Objects.equals(address, office.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId, address);
    }

    @Override
    public String toString() {
        return "Office{" +
                "officeId='" + officeId + '\'' +
                ", address=" + address +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String officeId;
        private Address address;

        public Builder withOfficeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Office build() {
            return new Office(officeId, address);
        }
    }
}
