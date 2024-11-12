package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.dto.RelatedCaseDto;
import com.hayes.pvtsys.dto.TestCaseDto;
import com.hayes.pvtsys.dto.TestResultDto;
import com.hayes.pvtsys.pojo.BaseTestCase;
import com.hayes.pvtsys.query.BaseQuery;
import com.hayes.pvtsys.query.CaseQuery;
import com.hayes.pvtsys.service.TestCaseService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/case")
public class CaseController {

    @Autowired
    private TestCaseService testCaseService;

    @PostMapping("/add")
    public HttpResult<Boolean> addCase(@RequestBody TestCaseDto testCaseDto){
        testCaseService.addCase(testCaseDto);
        return HttpResult.returnSuccess(true);
    }

    @PostMapping("/page")
    public HttpResult<PageResponse<TestResultDto>> findPage(@RequestBody CaseQuery query){
        PageResponse<TestResultDto> page = testCaseService.findPage(query);
        return HttpResult.returnSuccess(page);
    }

    @PostMapping("/base/page")
    public HttpResult<PageResponse<BaseTestCase>> findBaseCase(@RequestBody BaseQuery query){
        PageResponse<BaseTestCase> baseCasePage = testCaseService.findBaseCasePage(query);
        return HttpResult.returnSuccess(baseCasePage);
    }

    @PostMapping("/base/add")
    public HttpResult<Boolean> addBaseCase(@RequestBody BaseTestCase baseTestCase){
        testCaseService.addBaseCase(baseTestCase);
        return HttpResult.returnSuccess(true);
    }

    @DeleteMapping("/base/delete/{id}")
    public HttpResult<Boolean> deleteBaseCase(@PathVariable("id") int id){
        testCaseService.deleteBaseCase(id);
        return HttpResult.returnSuccess(true);
    }

    @GetMapping("/base/query/{id}")
    public HttpResult<BaseTestCase> queryBaseCase(@PathVariable("id") int id){
        BaseTestCase baseTestCase = testCaseService.queryBaseCase(id);
        return HttpResult.returnSuccess(baseTestCase);
    }

    @GetMapping("/relate/query/{deploymentId}")
    public HttpResult<List<BaseTestCase>> queryCaseIdWithCommon(@PathVariable("deploymentId") int deploymentId){
        List<BaseTestCase> baseTestCases = testCaseService.queryCaseIdWithCommon(deploymentId);
        return HttpResult.returnSuccess(baseTestCases);
    }

    @PostMapping("/relate/add")
    public HttpResult<Boolean> relateCase(@RequestBody RelatedCaseDto relatedCaseDto){
        testCaseService.addRelate(relatedCaseDto);
        return HttpResult.returnSuccess(true);
    }
}
