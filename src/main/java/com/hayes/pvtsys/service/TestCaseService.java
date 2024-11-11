package com.hayes.pvtsys.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.dto.TestCaseDto;
import com.hayes.pvtsys.dto.TestResultDto;
import com.hayes.pvtsys.enums.TestCagetoryEnum;
import com.hayes.pvtsys.enums.TestCaseEnum;
import com.hayes.pvtsys.enums.TestDeviceEnum;
import com.hayes.pvtsys.pojo.*;
import com.hayes.pvtsys.query.BaseQuery;
import com.hayes.pvtsys.query.CaseQuery;
import com.hayes.pvtsys.repository.BaseTicketCaseRepository;
import com.hayes.pvtsys.repository.TicketCaseRepository;
import com.hayes.pvtsys.repository.TicketRepository;
import com.hayes.pvtsys.repository.TicketResultRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class TestCaseService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketCaseRepository ticketCaseRepository;

    @Autowired
    private TicketResultRepository ticketResultRepository;

    @Autowired
    private BaseTicketCaseRepository baseTicketCaseRepository;

    @Transactional
    public void addCase(TestCaseDto testCaseDto){
        TestCase testCase = new TestCase();
        testCase.setTicketNo(testCaseDto.getTicketNo());
        testCase.setDescription(testCaseDto.getDescription());
        testCase.setSummary(testCaseDto.getSummary());
        testCase.setExpectedResult(testCaseDto.getExpectedResult());
        testCase.setType(testCaseDto.getType());
        testCase.setRowHeight(testCaseDto.rowHeight());
        testCase.setPriority(testCaseDto.getPriority());
        testCase = ticketCaseRepository.save(testCase);
        //add result
        int[] envList = testCaseDto.getEnvList();
        int[] device = testCaseDto.getDevice();
        List<TestResult> results = new ArrayList<>();
        for (int i: envList){
            for (int j: device){
                TestResult result = new TestResult();
                result.setTestCase(testCase);
                result.setCategory(i + j);
                results.add(result);
            }
        }
        ticketResultRepository.saveAll(results);

    }


    public PageResponse<TestResultDto> findPage(CaseQuery query){
        Pageable pageRequest = PageRequest.of(query.getPageNum() - 1, query.getPageSize());
        Page<Map<String, Object>> results = ticketCaseRepository.findPage(pageRequest, query);
        List<TestResultDto> testResults = new ArrayList<>();
        if (CollUtil.isNotEmpty(results.getContent())){
            for (Map<String, Object> result: results.getContent()){
                TestResultDto bean = JSONUtil.toBean(JSONUtil.toJsonStr(result), TestResultDto.class);
                testResults.add(bean);
            }
        }
        return new PageResponse<>(results.getNumber() + 1, results.getTotalPages(),((Long)results.getTotalElements()).intValue(), testResults);
    }

    /**
     *
     * base
     * @param query 查询条件
     * @return 分页记录
     */
    public PageResponse<BaseTestCase> findBaseCasePage(BaseQuery query){
        Pageable pageRequest = PageRequest.of(query.getPageNum() - 1, query.getPageSize());
        Page<BaseTestCase> baseTestCases = baseTicketCaseRepository.findPage(pageRequest);
        return new PageResponse<>(baseTestCases);
    }

    public void addBaseCase(BaseTestCase baseTestCase){
        int[] envList = baseTestCase.getEnvList();
        int[] deviceList = baseTestCase.getDevice();
        int sum = Arrays.stream(envList).sum() + Arrays.stream(deviceList).sum();
        baseTestCase.setCategory(sum);
        baseTestCase.setRowHeight(baseTestCase.rowHeight());
        baseTicketCaseRepository.save(baseTestCase);
    }

    public void deleteBaseCase(int baseCaseId){
        baseTicketCaseRepository.deleteById(baseCaseId);
    }

    public BaseTestCase queryBaseCase(int baseCaseId){
        BaseTestCase baseTestCase = baseTicketCaseRepository.findById(baseCaseId).orElseThrow();
        int[] envArray = TestCagetoryEnum.getAllEnvValueArray(baseTestCase.getCategory());
        int[] deviceArray = TestDeviceEnum.getAllDeviceValueArray(baseTestCase.getCategory());
        baseTestCase.setEnvList(envArray);
        baseTestCase.setDevice(deviceArray);
        return baseTestCase;
    }

    public List<BaseTestCase> queryCaseIdWithCommon(Integer deploymentId){
        String ticketNo = TestCaseEnum.COMMON_CASE.name() + "-" + deploymentId;
        return baseTicketCaseRepository.queryBaseExcluding(ticketNo);
    }
}
