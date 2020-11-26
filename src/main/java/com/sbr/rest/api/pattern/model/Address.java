package com.sbr.rest.api.pattern.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Address {

    private String city;
    private String state;
    private String postcode;
}
