package com.hayes.pvtsys.service;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.hayes.pvtsys.dto.UserDto;
import com.hayes.pvtsys.query.LoginQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(LoginQuery query){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(query.getAccount(), SecureUtil.sha256(query.getPassword()));
        Authentication authentication = authenticationManager.authenticate(token);
        if (authentication == null){
            //认证没有通过
            throw new RuntimeException("登录失败");
        }

        //认证通过使用userid生成一个jwt，
        UserDto loginUser = (UserDto)authentication.getPrincipal();
        Map<String, Object> map = new HashMap<>() {
            {
                put("uid", loginUser.getPvtUser().getId());
                put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
                put("loginUser", loginUser);
            }
        };
        return JWTUtil.createToken(map,loginUser.getUsername().getBytes());
    }
}
