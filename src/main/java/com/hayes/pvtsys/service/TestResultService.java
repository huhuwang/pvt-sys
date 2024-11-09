package com.hayes.pvtsys.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.hayes.pvtsys.pojo.Document;
import com.hayes.pvtsys.pojo.TestResult;
import com.hayes.pvtsys.query.CaseQuery;
import com.hayes.pvtsys.repository.TicketCaseRepository;
import com.hayes.pvtsys.repository.TicketResultRepository;
import com.hayes.pvtsys.util.ServerPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestResultService {

    @Autowired
    private TicketResultRepository ticketResultRepository;

    public TestResult queryResultById(int resultId){

       TestResult result = ticketResultRepository.findById(resultId).orElseThrow();
       if (CollUtil.isNotEmpty(result.getDocuments())){
           List<Document> documents = new ArrayList<>();
           result.getDocuments().forEach(document -> {
               Document documentClone = ObjectUtil.cloneByStream(document);
               documentClone.setUrl(ServerPath.partTomcat() + document.getUrl());
               documents.add(documentClone);
           });
           result.setDocuments(documents);
       }
       return result;
    }


    public void addResult(TestResult result){
        ticketResultRepository.save(result);
    }

    public List<Integer> queryCaseId(CaseQuery query){
        return ticketResultRepository.queryCaseId(query);
    }

    public void deleteCase(int resultId){
        ticketResultRepository.deleteById(resultId);
    }
}
