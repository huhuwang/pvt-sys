package com.hayes.pvtsys.fliter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import com.hayes.pvtsys.dto.UserDto;
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
        String token = request.getHeader("Authentication");
        if (StringUtils.isBlank(token)){
            //没有token放行，是因为后面的逻辑需要对token解析，没有token就不行了
            //放行没有问题因为后面的fliter会对进行认证，没有token会直接异常
            //该fliter主要是解析token的，不是认证的
            //加return是为了fliter回来的时候不在继续往下执行
            filterChain.doFilter(request, response);
            return;
        }

        //解析token
        //如果退出之后再登录，我用之前的老token还是可以认证成功。所以redis能否使用token（hash）作为key
        UserDto loginUser;
        try {
            JWT jwt = JWTUtil.parseToken(token);
            loginUser = (UserDto)jwt.getPayload("loginUser");
        } catch (Exception e){
            throw new RuntimeException("token不合法");
        }
        if (loginUser == null){
            throw new RuntimeException("用户未登录");
        }
        //这里必须用3个参数的构造方法，表示已经认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
