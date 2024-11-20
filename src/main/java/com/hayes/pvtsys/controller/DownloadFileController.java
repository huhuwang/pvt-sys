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

    @Autowired
    private DownloadService downloadService;
    @GetMapping("/us")
    public void downloadUS(HttpServletResponse response, String ticketNo, int env) {
        downloadService.downloadSU(response, ticketNo, env);
    }

    @GetMapping("/rt")
    public void downloadRT(HttpServletResponse response, Integer deploymentId){
        downloadService.downloadRT(response, deploymentId);
    }
}
