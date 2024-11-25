package com.hayes.pvtsys.fliter;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.hayes.pvtsys.dto.UserDto;
import com.hayes.pvtsys.enums.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFliter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token) || request.getRequestURI().contains("/login")){
            filterChain.doFilter(request, response);
            return;
        }
        UserDto loginUser;
        try {
            JWT jwt = JWTUtil.parseToken(token);
            loginUser = JSONUtil.toBean(JSONUtil.toJsonStr(jwt.getPayload("loginUser")), UserDto.class);
        } catch (Exception e){
            throw new RuntimeException(Constants.TOKEN_FORBIDDEN);
        }
        if (loginUser == null){
            throw new RuntimeException(Constants.LOGIN_FORBIDDEN);
        }
        //这里必须用3个参数的构造方法，表示已经认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
