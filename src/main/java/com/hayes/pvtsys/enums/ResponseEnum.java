package com.hayes.pvtsys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS("200", "请求成功"),

    FAIL("500", "服务器异常"),


    ;

    public String code;

    public String message;
}
