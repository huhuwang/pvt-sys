package com.hayes.pvtsys.util;

import cn.hutool.http.HttpStatus;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WebUtil {

    public static void response(HttpServletResponse response, String content) throws IOException {
        response.setStatus(HttpStatus.HTTP_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(content);
    }
}
