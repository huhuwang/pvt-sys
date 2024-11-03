package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.dto.TestCaseDto;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/case")
public class CaseController {

    @PostMapping("/add")
    public HttpResult<Boolean> addCase(@RequestBody TestCaseDto testCaseDto){

        int rowHeight = testCaseDto.rowHeight();
        return HttpResult.returnSuccess(true);
    }
}
