package com.hayes.pvtsys.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestCaseEnum {
    TICKET_CASE("TICKET", 1),
    COMMON_CASE("COMMON", 2);

    private final String name;
    private final int value;
}
