package com.hayes.pvtsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String redirectToHome() {
        return "redirect:/index.html";
    }

}