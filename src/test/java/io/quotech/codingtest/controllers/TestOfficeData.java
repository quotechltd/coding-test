package io.quotech.codingtest.controllers;

import io.quotech.codingtest.integrations.Address;
import io.quotech.codingtest.integrations.Office;

import java.util.List;

public class TestOfficeData {

    private static final Office QUOTECH_EDINBURGH = Office.builder()
            .withOfficeId("edinburgh")
            .withAddress(Address.builder()
                    .withFirstLine("6 Princes Street")
                    .withSecondLine("")
                    .withCity("Edinburgh")
                    .withPostcode("E1 7RX")
                    .build())
            .build();

    private static final Office QUOTECH_MANCHESTER = Office.builder()
            .withOfficeId("manchester")
            .withAddress(Address.builder()
                    .withFirstLine("60 Deansgate")
                    .withSecondLine("")
                    .withCity("Manchester")
                    .withPostcode("M2 8EX")
                    .build())
            .build();

    private static final Office LLOYDS_MANCHESTER = Office.builder()
            .withOfficeId("manchester")
            .withAddress(Address.builder()
                    .withFirstLine("42 Market Street")
                    .withSecondLine("")
                    .withCity("Manchester")
                    .withPostcode("M1 1AB")
                    .build())
            .build();

    private static final List<Office> QUOTECH_OFFICES = List.of(QUOTECH_EDINBURGH, QUOTECH_MANCHESTER);

    private static final List<Office> LLOYDS_OFFICES = List.of(LLOYDS_MANCHESTER);
}
