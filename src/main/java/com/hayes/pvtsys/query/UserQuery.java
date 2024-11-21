package com.hayes.pvtsys.query;

import lombok.Data;

@Data
public class UserQuery extends BaseQuery{

    private String userName;

    private String account;

    private Integer role;
}
