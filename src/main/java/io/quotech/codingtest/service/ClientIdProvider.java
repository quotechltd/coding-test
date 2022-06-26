package io.quotech.codingtest.service;

import org.springframework.stereotype.Component;

@Component
public class ClientIdProvider {

    public String getClientId() {
        return "my-insurance-co";
    }
}
