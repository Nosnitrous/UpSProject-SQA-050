package com.luxoft.unit;

import lombok.Getter;

@Getter
public enum TestEnum {
    KID(8,"no"),
    TEEN(14,"half"),
    ADULT(18,"yea");

    private int age;
    private String response;


    TestEnum(int age, String response) {
        this.age = age;
        this.response = response;
    }
}
