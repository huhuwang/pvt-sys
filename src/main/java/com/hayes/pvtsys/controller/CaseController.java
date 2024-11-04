package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.dto.TestCaseDto;
import com.hayes.pvtsys.dto.TestResultDto;
import com.hayes.pvtsys.query.CaseQuery;
import com.hayes.pvtsys.service.TestCaseService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
