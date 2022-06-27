package io.quotech.codingtest.integrations;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfficeClient {

    public List<Office> getAddressForClient(String clientId) {
        return List.of();
    }
}
