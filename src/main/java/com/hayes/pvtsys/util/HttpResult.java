package com.hayes.pvtsys.util;

import com.hayes.pvtsys.enums.ResponseEnum;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
public class HttpResult<T> implements Serializable {
	
    private static final long serialVersionUID = 3854679720587282344L;

    private String code;
    
    private String message;
    
    private T datas;

    private HttpResult(T datas) {
        this(ResponseEnum.SUCCESS, datas);
    }

    private HttpResult(ResponseEnum responseContent, T datas) {
        this.datas = datas;
        this.code = responseContent.getCode();
        this.message = responseContent.getMessage();
    }
    
    private HttpResult(String code, String message, T datas) {
    	this.code = code;
    	this.message = message;
    	this.datas = datas;
    }

    public static <T> HttpResult<T> returnSuccess() {
        return new HttpResult<>(null);
    }

    public static <T> HttpResult<T> returnSuccess(T datas) {
        return new HttpResult<>(datas);
    }

    public static <T> HttpResult<T> returnSuccess(String message, T datas) {
        return new HttpResult<>(ResponseEnum.SUCCESS.getCode(),message,datas);
    }
    
    public static <T> HttpResult<T> returnFail() {
        return new HttpResult<>(ResponseEnum.FAIL, null);
    }

    public static <T> HttpResult<T> returnFail(ResponseEnum responseCode, T datas) {
        return new HttpResult<>(responseCode, datas);
    }
    
    public static <T> HttpResult<T> returnFail(String message, T datas) {
    	return new HttpResult<>(ResponseEnum.FAIL.getCode(),message, datas);
    }
    
    public static <T> HttpResult<T> returnFail(String message) {
    	return returnFail(message, null);
    }

    public static <T> HttpResult<T> returnFail(ResponseEnum responseEnum) {
        return returnFail(responseEnum, null);
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

}
