package com.hayes.pvtsys.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hayes.pvtsys.pojo.Document;
import com.hayes.pvtsys.pojo.TestResult;
import com.hayes.pvtsys.repository.DocumentRepository;
import com.hayes.pvtsys.repository.TicketResultRepository;
import com.hayes.pvtsys.util.ServerPath;
import jakarta.servlet.http.HttpServletRequest;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class DocumentService {


    @Autowired
    private TicketResultRepository ticketResultRepository;

    @Autowired
    private DocumentRepository documentRepository;

    public Document uploadDocument(HttpServletRequest request, MultipartFile file){
        String resultId = request.getParameter("resultId");
        String deploymentId = request.getParameter("deploymentId");
        if (StrUtil.isNotBlank(resultId) && StrUtil.isNotBlank(deploymentId)
                && !StrUtil.equalsIgnoreCase(resultId, "undefined") && !StrUtil.equalsIgnoreCase(deploymentId, "undefined")
                && !file.isEmpty()){
            int result = Integer.parseInt(resultId);
            TestResult testResult = ticketResultRepository.findById(result).orElseThrow();
            Document document = new  Document();
            String documentId = UUID.randomUUID().toString();
            document.setId(documentId);
            document.setDocumentName(file.getOriginalFilename());
            document.setOriginalSize(file.getSize());
            document.setDocumentType(file.getContentType());
            document.setResultId(result);
            String device = testResult.getDevice();
            int width = 480;
            int height = 250;
            if ("IPAD".equalsIgnoreCase(device)){
                height = 360;
            }

            String serverPath = ServerPath.relativePath(deploymentId, testResult.getTestCase().getTicketNo()) + "/" + documentId + ".jpg";
            File fileTargetPath = new File(ServerPath.partOutPath() + serverPath) ;
            try (InputStream inputStream = file.getInputStream()){
                Thumbnails.of(inputStream)
                        .forceSize(width, height)
                        .outputFormat("jpg")
                        .toFile(fileTargetPath);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
            document.setUrl(serverPath);
            document.setScaleSize(fileTargetPath.length());
            document = documentRepository.save(document);
            Document documentClone = ObjectUtil.cloneByStream(document);
            documentClone.setUrl(ServerPath.partTomcat() + serverPath);

            System.out.println(document);
            System.out.println(documentClone);
            return documentClone;
        }
        throw new RuntimeException("上传失败");
    }

    public void deleteDocument(String documentId){
        documentRepository.deleteById(documentId);
    }
}
