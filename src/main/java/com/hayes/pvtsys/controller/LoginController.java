package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.query.LoginQuery;
import com.hayes.pvtsys.service.LoginService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public HttpResult<String> login(@RequestBody LoginQuery query){
        String token = loginService.login(query);
        return HttpResult.returnSuccess(token);
    }

    @PostMapping("/out")
    public HttpResult<Boolean> loginOut(){
        loginService.loginOut();
        return HttpResult.returnSuccess(true);
    }
}
