package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.dto.RelatedCaseDto;
import com.hayes.pvtsys.dto.TestCaseDto;
import com.hayes.pvtsys.dto.TestResultDto;
import com.hayes.pvtsys.pojo.BaseTestCase;
import com.hayes.pvtsys.query.BaseCaseQuery;
import com.hayes.pvtsys.query.CaseQuery;
import com.hayes.pvtsys.service.TestCaseService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/case")
public class CaseController {

    @Autowired
    private TestCaseService testCaseService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('add-case')")
    public HttpResult<Boolean> addCase(@RequestBody TestCaseDto testCaseDto){
        testCaseService.addCase(testCaseDto);
        return HttpResult.returnSuccess(true);
    }

    @PostMapping("/page")
    @PreAuthorize("hasAnyAuthority('list-case')")
    public HttpResult<PageResponse<TestResultDto>> findPage(@RequestBody CaseQuery query){
        PageResponse<TestResultDto> page = testCaseService.findPage(query);
        return HttpResult.returnSuccess(page);
    }

    @PostMapping("/base/page")
    @PreAuthorize("hasAnyAuthority('list-base-case')")
    public HttpResult<PageResponse<BaseTestCase>> findBaseCase(@RequestBody BaseCaseQuery query){
        PageResponse<BaseTestCase> baseCasePage = testCaseService.findBaseCasePage(query);
        return HttpResult.returnSuccess(baseCasePage);
    }

    @PostMapping("/base/add")
    @PreAuthorize("hasAnyAuthority('add-base-case')")
    public HttpResult<Boolean> addBaseCase(@RequestBody BaseTestCase baseTestCase){
        testCaseService.addBaseCase(baseTestCase);
        return HttpResult.returnSuccess(true);
    }

    @DeleteMapping("/base/delete/{id}")
    @PreAuthorize("hasAnyAuthority('delete-base-case')")
    public HttpResult<Boolean> deleteBaseCase(@PathVariable("id") int id){
        testCaseService.deleteBaseCase(id);
        return HttpResult.returnSuccess(true);
    }

    @GetMapping("/base/query/{id}")
    @PreAuthorize("hasAnyAuthority('query-base-case')")
    public HttpResult<BaseTestCase> queryBaseCase(@PathVariable("id") int id){
        BaseTestCase baseTestCase = testCaseService.queryBaseCase(id);
        return HttpResult.returnSuccess(baseTestCase);
    }

    @PostMapping("/relate/query")
    @PreAuthorize("hasAnyAuthority('relate-query-base-case')")
    public HttpResult<List<BaseTestCase>> queryCaseIdWithCommon(@RequestBody BaseCaseQuery query){
        List<BaseTestCase> baseTestCases = testCaseService.queryCaseIdWithCommon(query);
        return HttpResult.returnSuccess(baseTestCases);
    }

    @PostMapping("/relate/add")
    @PreAuthorize("hasAnyAuthority('relate-add-case')")
    public HttpResult<Boolean> relateCase(@RequestBody RelatedCaseDto relatedCaseDto){
        testCaseService.addRelate(relatedCaseDto);
        return HttpResult.returnSuccess(true);
    }
}
