package com.sbr.rest.api.pattern.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Contact {

    private String type;

    private String detail;
}
