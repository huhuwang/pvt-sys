package com.hayes.pvtsys.service;

import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.dto.RTTemplateDto;
import com.hayes.pvtsys.enums.Constants;
import com.hayes.pvtsys.pojo.KVConstants;
import com.hayes.pvtsys.pojo.RTTemplate;
import com.hayes.pvtsys.query.RTTemplateQuery;
import com.hayes.pvtsys.repository.KVRepository;
import com.hayes.pvtsys.repository.RTTemplateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RTTemplateService {

    @Autowired
    private RTTemplateRepository rtTemplateRepository;

    @Autowired
    private KVRepository kvRepository;

    public PageResponse<RTTemplate> findPage(RTTemplateQuery query){
        Pageable pageRequest = PageRequest.of(query.getPageNum() - 1, query.getPageSize(), Sort.by(Sort.Direction.DESC, "createTime", "id"));
        Page<RTTemplate> rtTemplates = rtTemplateRepository.findPage(pageRequest, query);
        return new PageResponse<>(rtTemplates);
    }

    @Transactional
    public void addRTTemplate(RTTemplateDto rtTemplateDto){
        String templateName = rtTemplateDto.getTemplateName();
        String application = rtTemplateDto.getApplication();
        Integer flowNumber = rtTemplateDto.getFlowNumber();
        RTTemplate template = new RTTemplate();
        template.setTemplateName(templateName);
        template.setApplication(application);
        template.setFlowNumber(flowNumber);
        template = rtTemplateRepository.save(template);

        kvRepository.deleteKVConstantsByTemplateId(template.getId());
        List<Map<String, String>> rtFlow = rtTemplateDto.getRtFlow();
        for (Map<String, String> flow : rtFlow){
            String flowPoint = flow.get("flowPoint");
            KVConstants kvConstants = new KVConstants();
            kvConstants.setKeyName("flowPoint");
            kvConstants.setKeyValue(flowPoint);
            kvConstants.setConstantType(Constants.CONSTANTS_TYPE_FLOW_STEP);
            kvConstants.setTemplateId(template.getId());
            kvConstants = kvRepository.save(kvConstants);
            List<KVConstants> dataKVConstants = new ArrayList<>();
            for (int i = 1; i <= flowNumber; i ++){
                String keyName = "RT" + i;
                String flowData = flow.get("keyName");
                KVConstants dataConstants = new KVConstants();
                dataConstants.setKeyName(keyName);
                dataConstants.setKeyValue(flowData);
                dataConstants.setConstantType(Constants.CONSTANTS_TYPE_FLOW_DATA);
                dataConstants.setParentId(kvConstants.getId());
                dataConstants.setTemplateId(template.getId());
                dataKVConstants.add(dataConstants);
            }
            kvRepository.saveAll(dataKVConstants);
        }
    }
}
