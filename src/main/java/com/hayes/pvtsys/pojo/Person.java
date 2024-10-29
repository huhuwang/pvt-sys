package com.hayes.pvtsys.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Person {

    private String name;

    private int age;

    private String address;

    private String idNo;

    private String email;

    private LocalDate birthDay;
}
