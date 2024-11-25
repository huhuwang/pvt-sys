package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.pojo.Document;
import com.hayes.pvtsys.service.DocumentService;
import com.hayes.pvtsys.util.HttpResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    @PostMapping("/upload")
    @PreAuthorize("hasAnyAuthority('upload-evidence')")
    public HttpResult<Document> handleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        Document document = documentService.uploadDocument(request, file);
        return HttpResult.returnSuccess(document);
    }

    @DeleteMapping("/delete/{documentId}")
    @PreAuthorize("hasAnyAuthority('delete-evidence')")
    public HttpResult<Boolean> deleteFile(@PathVariable("documentId") String documentId){
        documentService.deleteDocument(documentId);
        return HttpResult.returnSuccess(true);
    }
}
