package com.hayes.pvtsys.service;

import com.hayes.pvtsys.pojo.TestResult;
import com.hayes.pvtsys.repository.TicketResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestResultService {

    @Autowired
    private TicketResultRepository ticketResultRepository;

    public TestResult queryResultById(int resultId){

       return ticketResultRepository.findById(resultId).orElseThrow();
    }
}
