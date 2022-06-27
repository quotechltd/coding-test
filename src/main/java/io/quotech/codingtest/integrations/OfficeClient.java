package io.quotech.codingtest.integrations;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfficeClient {

    public List<Office> getAddressesForOrganisation(String organisationId) {
        throw new UnsupportedOperationException();
    }
}
