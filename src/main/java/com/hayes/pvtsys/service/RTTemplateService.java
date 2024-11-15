package com.hayes.pvtsys.service;

import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.pojo.RTTemplate;
import com.hayes.pvtsys.query.RTTemplateQuery;
import com.hayes.pvtsys.repository.RTTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RTTemplateService {

    @Autowired
    private RTTemplateRepository rtTemplateRepository;

    public PageResponse<RTTemplate> findPage(RTTemplateQuery query){
        Pageable pageRequest = PageRequest.of(query.getPageNum() - 1, query.getPageSize(), Sort.by(Sort.Direction.DESC, "createTime", "id"));
        Page<RTTemplate> rtTemplates = rtTemplateRepository.findPage(pageRequest, query);
        return new PageResponse<>(rtTemplates);
    }
}
