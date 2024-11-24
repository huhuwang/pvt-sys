package com.hayes.pvtsys.handler;

import cn.hutool.http.HttpStatus;
import com.hayes.pvtsys.enums.Constants;
import com.hayes.pvtsys.util.HttpResult;
import com.hayes.pvtsys.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 权限不足的异常统一处理
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(String.valueOf(HttpStatus.HTTP_FORBIDDEN));
        httpResult.setMessage(Constants.ACCESS_FORBIDDEN);

        WebUtil.response(response, httpResult.toString());
    }
}
