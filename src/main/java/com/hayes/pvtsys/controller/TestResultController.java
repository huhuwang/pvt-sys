package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.pojo.TestResult;
import com.hayes.pvtsys.service.TestResultService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/result")
public class TestResultController {

    @Autowired
    private TestResultService testResultService;

    @GetMapping("/query/{id}")
    public HttpResult<TestResult> queryResultById(@PathVariable("id") int id){
        TestResult result = testResultService.queryResultById(id);
        return HttpResult.returnSuccess(result);
    }

}
