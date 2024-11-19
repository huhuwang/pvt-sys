package com.hayes.pvtsys.controller;


import com.hayes.pvtsys.service.DownloadService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/download")
public class DownloadFileController {
    ////http://localhost:8080/download/uat?ticketNo=IRP-22079&env=1

    @Autowired
    private DownloadService downloadService;
    @GetMapping("/uat")
    public void downloadUS(HttpServletResponse response, String ticketNo, int env) {
        downloadService.downloadSU(response, ticketNo, env);
    }
}
