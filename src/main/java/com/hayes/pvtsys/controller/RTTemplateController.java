package com.hayes.pvtsys.controller;

import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.dto.RTTemplateDetail;
import com.hayes.pvtsys.dto.RTTemplateDto;
import com.hayes.pvtsys.pojo.RTTemplate;
import com.hayes.pvtsys.query.RTTemplateQuery;
import com.hayes.pvtsys.service.RTTemplateService;
import com.hayes.pvtsys.util.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/delete/{id}")
    public HttpResult<Boolean> deleteRTTemplate(@PathVariable("id") int rtTemplateId){
        rtTemplateService.deleteRTTemplate(rtTemplateId);
        return HttpResult.returnSuccess(true);
    }

    @GetMapping("/query/{id}")
    public HttpResult<RTTemplateDetail> queryRTTemplateDetail(@PathVariable("id") int rtTemplateId){
        RTTemplateDetail detail = rtTemplateService.queryRTTemplateDetail(rtTemplateId);
        return HttpResult.returnSuccess(detail);
    }

    @PostMapping("/copy/{id}")
    public HttpResult<Boolean> copyRTTemplateDetail(@PathVariable("id") int rtTemplateId){
        rtTemplateService.cloneFromOldOne(rtTemplateId);
        return HttpResult.returnSuccess(true);
    }

    @PostMapping("/query/with/application")
    public HttpResult<List<RTTemplate>> queryRTTemplateByApplicationAndStatus(@RequestBody RTTemplateQuery query){
        List<RTTemplate> rtTemplates = rtTemplateService.queryRTTemplateByApplicationAndStatus(query);
        return HttpResult.returnSuccess(rtTemplates);
    }
}
