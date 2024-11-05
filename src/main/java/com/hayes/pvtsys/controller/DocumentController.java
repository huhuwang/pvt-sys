package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.pojo.Document;
import com.hayes.pvtsys.service.DocumentService;
import com.hayes.pvtsys.util.HttpResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    @PostMapping("/upload")
    public HttpResult<Document> handleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        Document document = documentService.uploadDocument(request, file);
        return HttpResult.returnSuccess(document);
    }
}
