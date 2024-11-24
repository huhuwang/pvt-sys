package com.hayes.pvtsys.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginQuery implements Serializable {

    private String account;

    private String password;
}
