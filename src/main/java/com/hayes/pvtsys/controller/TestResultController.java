package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.pojo.TestResult;
import com.hayes.pvtsys.query.CaseQuery;
import com.hayes.pvtsys.service.TestResultService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    public HttpResult<Boolean> addResult(@RequestBody TestResult result){
        testResultService.addResult(result);
        return HttpResult.returnSuccess(true);
    }
    @PostMapping("query/case")
    public HttpResult<List<Integer>> queryCaseId(@RequestBody CaseQuery query){
        List<Integer> caseId = testResultService.queryCaseId(query);
        return HttpResult.returnSuccess(caseId);
    }

}
