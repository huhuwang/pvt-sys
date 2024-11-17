package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.dto.RTTemplateDto;
import com.hayes.pvtsys.pojo.RTTemplate;
import com.hayes.pvtsys.query.RTTemplateQuery;
import com.hayes.pvtsys.service.RTTemplateService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rt")
public class RTTemplateController {

    @Autowired
    private RTTemplateService rtTemplateService;

    @PostMapping("/page")
    public HttpResult<PageResponse<RTTemplate>> pageRTTemplate(@RequestBody RTTemplateQuery query){
        PageResponse<RTTemplate> page = rtTemplateService.findPage(query);
        return HttpResult.returnSuccess(page);
    }

    @PostMapping("/add")
    public HttpResult<Boolean> addRTTemplate(@RequestBody RTTemplateDto rtTemplateDto){
        rtTemplateService.addRTTemplate(rtTemplateDto);
        return HttpResult.returnSuccess(true);
    }


}
