package com.hayes.pvtsys.controller;


import com.hayes.pvtsys.service.DownloadService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class DownloadFileController {

    @Autowired
    private DownloadService downloadService;
    @GetMapping("/us")
    @PreAuthorize("hasAnyAuthority('export-sit')")
    public void downloadUS(HttpServletResponse response, String ticketNo, int env) {
        downloadService.downloadSU(response, ticketNo, env);
    }

    @GetMapping("/rt")
    @PreAuthorize("hasAnyAuthority('export-rt')")
    public void downloadRT(HttpServletResponse response, Integer deploymentId){
        downloadService.downloadRT(response, deploymentId);
    }

    @GetMapping("/pvt")
    @PreAuthorize("hasAnyAuthority('export-pvt')")
    public void downloadPVT(HttpServletResponse response, Integer deploymentId){

    }
}
