package com.hayes.pvtsys.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.dto.RelatedCaseDto;
import com.hayes.pvtsys.dto.TestCaseDto;
import com.hayes.pvtsys.dto.TestResultDto;
import com.hayes.pvtsys.enums.Constants;
import com.hayes.pvtsys.enums.TestCagetoryEnum;
import com.hayes.pvtsys.enums.TestDeviceEnum;
import com.hayes.pvtsys.pojo.*;
import com.hayes.pvtsys.query.BaseCaseQuery;
import com.hayes.pvtsys.query.BaseQuery;
import com.hayes.pvtsys.query.CaseQuery;
import com.hayes.pvtsys.repository.BaseTicketCaseRepository;
import com.hayes.pvtsys.repository.TicketCaseRepository;
import com.hayes.pvtsys.repository.TicketRepository;
import com.hayes.pvtsys.repository.TicketResultRepository;
import com.hayes.pvtsys.util.ServerPath;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

        ticketResultRepository.saveAll(createResults(testCaseDto.getCategory(), testCase, testCaseDto.getRtFlow()));
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
        baseTestCase.setRowHeight(baseTestCase.rowHeight());
        baseTicketCaseRepository.save(baseTestCase);
    }

    public void deleteBaseCase(int baseCaseId){
        baseTicketCaseRepository.deleteById(baseCaseId);
    }

    public BaseTestCase queryBaseCase(int baseCaseId){
        return baseTicketCaseRepository.findById(baseCaseId).orElseThrow();
    }

    public List<BaseTestCase> queryCaseIdWithCommon(BaseCaseQuery query){
        Integer deploymentId = query.getDeploymentId();
        String ticketNo = Constants.COMMON_TICKET_NAME + "-" + deploymentId;
        query.setTicketNo(ticketNo);
        return baseTicketCaseRepository.queryBaseExcluding(query);
    }

    @Transactional
    public void addRelate(RelatedCaseDto relatedCaseDto){
        if (CollUtil.isNotEmpty(relatedCaseDto.getCaseIds())){
            int deploymentId = relatedCaseDto.getDeploymentId();
            Set<Integer> caseIds = relatedCaseDto.getCaseIds();
            String ticketNo = Constants.COMMON_TICKET_NAME + "-" + deploymentId;
            Ticket ticket = ticketRepository.findTicketByTicketNoAndDeploymentId(ticketNo, deploymentId);
            if (ticket == null){
                //新增ticket
                ticket = new Ticket();
                ticket.setDeploymentId(deploymentId);
                ticket.setTicketNo(ticketNo);
                ticket.setType((byte) 2);
                ticket.setTicketTitle(Constants.COMMON_TICKET_TITLE);
                ticket.setTicketUrl(Constants.COMMON_TICKET_DETAIL);
                ticket.setTicketType(Constants.COMMON_TICKET_TYPE);

                String path = ServerPath.outPath(ticket.getDeploymentId().toString(), ticket.getTicketNo());
                FileUtil.mkdir(path);

                ticketRepository.saveAndFlush(ticket);
            }
            //add case & result
            BaseCaseQuery query = new BaseCaseQuery();
            query.setTicketNo(ticketNo);
            query.setBaseCaseIds(caseIds);
            List<BaseTestCase> baseTestCases = baseTicketCaseRepository.queryBase(query);
            for (BaseTestCase baseTestCase: baseTestCases){
                TestCase testCase = getTestCase(baseTestCase, ticketNo);
                ticketCaseRepository.saveAndFlush(testCase);
                Integer category = baseTestCase.getCategory();
                ticketResultRepository.saveAllAndFlush(createResults(category, testCase, baseTestCase.getRtFlow()));
            }
        }
    }

    private List<TestResult> createResults(int category, TestCase testCase, Integer rtFlow){
        List<Integer> envList = TestCagetoryEnum.getAllEnvValue(category);
        List<Integer> deviceList = TestDeviceEnum.getAllDeviceValue(category);
        List<TestResult> results = new ArrayList<>();
        for (int i: envList){
            for (int j: deviceList){
                TestResult result = new TestResult();
                result.setTestCase(testCase);
                result.setCategory(i + j);
                if ((i & TestCagetoryEnum.RT.getValue()) > 0){
                    result.setRtFlow(rtFlow);
                }
                results.add(result);
            }
        }
        return results;
    }
    private TestCase getTestCase(BaseTestCase baseTestCase, String ticketNo) {
        TestCase testCase = new TestCase();
        testCase.setTicketNo(ticketNo);
        testCase.setDescription(baseTestCase.getDescription());
        testCase.setSummary(baseTestCase.getSummary());
        testCase.setExpectedResult(baseTestCase.getExpectedResult());
        testCase.setPriority(baseTestCase.getPriority());
        testCase.setType(baseTestCase.getType());
        testCase.setRowHeight(baseTestCase.getRowHeight());
        testCase.setBaseCaseFrom(baseTestCase.getId());
        return testCase;
    }
}
