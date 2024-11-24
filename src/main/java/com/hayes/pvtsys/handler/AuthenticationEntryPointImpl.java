package com.hayes.pvtsys.handler;

import cn.hutool.http.HttpStatus;
import com.hayes.pvtsys.util.HttpResult;
import com.hayes.pvtsys.util.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 捕捉认证失败的异常
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(String.valueOf(HttpStatus.HTTP_UNAUTHORIZED));
        httpResult.setMessage( "用户认证失败");
        WebUtil.response(response, httpResult.toString());
    }
}
