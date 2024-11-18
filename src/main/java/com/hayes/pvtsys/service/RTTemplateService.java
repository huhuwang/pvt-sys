package com.hayes.pvtsys.service;

import cn.hutool.core.collection.CollUtil;
import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.dto.RTTemplateDetail;
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

import java.util.*;

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
        Integer id = rtTemplateDto.getId();
        RTTemplate template;
        if (id != null){
            template = rtTemplateRepository.findById(id).orElse(new RTTemplate());
        } else {
            template = new RTTemplate();
        }
        String templateName = rtTemplateDto.getTemplateName();
        String application = rtTemplateDto.getApplication();
        Integer flowNumber = rtTemplateDto.getFlowNumber();
        template.setTemplateName(templateName);
        template.setApplication(application);
        template.setFlowNumber(flowNumber);
        template.setStatus(Constants.STATUS_OK);
        template = rtTemplateRepository.save(template);

        kvRepository.deleteKVConstantsByTemplateId(template.getId());
        List<Map<String, String>> rtFlow = rtTemplateDto.getRtFlow();
        for (Map<String, String> flow : rtFlow){
            String flowPoint = flow.get(Constants.KV_MAIN_NAME);
            KVConstants kvConstants = new KVConstants();
            kvConstants.setKeyName(Constants.KV_MAIN_NAME);
            kvConstants.setKeyValue(flowPoint);
            kvConstants.setConstantType(Constants.CONSTANTS_TYPE_FLOW_STEP);
            kvConstants.setTemplateId(template.getId());
            kvConstants = kvRepository.save(kvConstants);
            List<KVConstants> dataKVConstants = new ArrayList<>();
            for (int i = 1; i <= flowNumber; i ++){
                String keyName = "RT" + i;
                String flowData = flow.get(keyName);
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

    @Transactional
    public void deleteRTTemplate(int rtTemplateId){
        rtTemplateRepository.deleteById(rtTemplateId);
        kvRepository.deleteKVConstantsByTemplateId(rtTemplateId);
    }

    public RTTemplateDetail queryRTTemplateDetail(int rtTemplateId){
        RTTemplate template = rtTemplateRepository.findById(rtTemplateId).orElseThrow();
        RTTemplateDetail detail = new RTTemplateDetail();
        detail.setId(template.getId());
        detail.setApplication(template.getApplication());
        detail.setFlowNumber(template.getFlowNumber());
        detail.setTemplateName(template.getTemplateName());
        detail.setStatus(template.getStatus());
        detail.setCreateUser(template.getCreateUser());
        detail.setCreateTime(template.getCreateTime());
        detail.setUpdateUser(template.getUpdateUser());
        detail.setUpdateTime(template.getUpdateTime());
        detail.setRemark(template.getRemark());

        List<Map<String, String>> response = new ArrayList<>();
        List<KVConstants> kvConstants = template.getKvConstants();
        if (CollUtil.isNotEmpty(kvConstants)){
            Map<Integer, Map<String,String>> allData = new LinkedHashMap<>();
            for (KVConstants kv : kvConstants){
                Integer id = kv.getId();
                String keyName = kv.getKeyName();
                String keyValue = kv.getKeyValue();
                Integer parentId = kv.getParentId();
                if (Constants.KV_MAIN_NAME.equalsIgnoreCase(keyName) && parentId == null){
                    createFlowData(allData, id, keyName, keyValue);
                } else {
                    createFlowData(allData, parentId, keyName, keyValue);
                }
            }
            response = new ArrayList<>(allData.values());
        }
        detail.setRtFlow(response);

        return detail;
    }


    @Transactional
    public void cloneFromOldOne(int rtTemplateId){
        RTTemplate template = rtTemplateRepository.findById(rtTemplateId).orElseThrow();
        RTTemplate newRTTemplate = new RTTemplate();
        newRTTemplate.setTemplateName(template.getTemplateName());
        newRTTemplate.setApplication(template.getApplication());
        newRTTemplate.setFlowNumber(template.getFlowNumber());
        newRTTemplate.setStatus(Constants.STATUS_OK);
        newRTTemplate = rtTemplateRepository.save(newRTTemplate);

        List<KVConstants> kvConstants = template.getKvConstants();
        if (CollUtil.isNotEmpty(kvConstants)){
            Map<Integer,  List<KVConstants>> allData = new LinkedHashMap<>();
            for (KVConstants kv : kvConstants){
                Integer id = kv.getId();
                String keyName = kv.getKeyName();
                Integer parentId = kv.getParentId();
                if (Constants.KV_MAIN_NAME.equalsIgnoreCase(keyName) && parentId == null){
                    createFlowData(allData, id, kv);
                } else {
                    createFlowData(allData, parentId, kv);
                }
            }

            for (Map.Entry<Integer, List<KVConstants>> entry : allData.entrySet()) {
                Integer mainId = entry.getKey();
                List<KVConstants> KVConstantsList = entry.getValue();
                KVConstants mainKV = KVConstantsList.stream().filter(e -> e.getId().equals(mainId)).toList().get(0);
                List<KVConstants> childList = KVConstantsList.stream().filter(e -> !e.getId().equals(mainId)).toList();

                KVConstants parent = new KVConstants();
                parent.setTemplateId(newRTTemplate.getId());
                parent.setKeyName(mainKV.getKeyName());
                parent.setKeyValue(mainKV.getKeyValue());
                parent.setConstantType(Constants.CONSTANTS_TYPE_FLOW_STEP);
                parent = kvRepository.save(parent);
                List<KVConstants> childDataList = getKvConstants(childList, newRTTemplate.getId(), parent.getId());
                kvRepository.saveAll(childDataList);
            }
        }
    }

    public List<RTTemplate> queryRTTemplateByApplicationAndStatus(RTTemplateQuery query){
        List<String> applications = query.getApplication();
        String application = null;
        if (CollUtil.isNotEmpty(applications)){
            application = applications.get(0);
        }
       return rtTemplateRepository.findRTTemplateByApplicationAndStatusOrderByIdDesc(application, Constants.STATUS_OK);
    }

    public void updateStatus(int rtTemplateId){
        RTTemplate template = rtTemplateRepository.findById(rtTemplateId).orElseThrow();
        byte status = template.getStatus(); //1 2
        byte newStatus = (byte) (status % 2 + 1);
        template.setStatus(newStatus);
        rtTemplateRepository.save(template);
    }
    private static List<KVConstants> getKvConstants(List<KVConstants> childList,Integer templateId, Integer parentId) {
        List<KVConstants> childDataList = new ArrayList<>();
        for (KVConstants kv : childList){
            KVConstants child = new KVConstants();
            child.setKeyName(kv.getKeyName());
            child.setKeyValue(kv.getKeyValue());
            child.setTemplateId(templateId);
            child.setParentId(parentId);
            child.setConstantType(Constants.CONSTANTS_TYPE_FLOW_DATA);
            childDataList.add(child);
        }
        return childDataList;
    }

    private void  createFlowData(Map<Integer, Map<String,String>> res, Integer id, String k, String v){
        Map<String, String> dataDetail;
        if (res.containsKey(id)){
            dataDetail = res.get(id);
        } else {
            dataDetail = new HashMap<>();
        }
        dataDetail.put(k, v);
        res.put(id, dataDetail);
    }

    private void  createFlowData(Map<Integer, List<KVConstants>> res, Integer id, KVConstants KV){
        List<KVConstants> kvConstants;
        if (res.containsKey(id)){
            kvConstants = res.get(id);
        } else {
            kvConstants = new ArrayList<>();
        }
        kvConstants.add(KV);
        res.put(id, kvConstants);
    }
}
