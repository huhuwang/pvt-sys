package com.hayes.pvtsys.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.hayes.pvtsys.dto.PageResponse;
import com.hayes.pvtsys.dto.RelatedCaseDto;
import com.hayes.pvtsys.dto.TestCaseDto;
import com.hayes.pvtsys.dto.TestResultDto;
import com.hayes.pvtsys.enums.Constants;
import com.hayes.pvtsys.enums.TestCategoryEnum;
import com.hayes.pvtsys.enums.TestEnvEnum;
import com.hayes.pvtsys.pojo.BaseTestCase;
import com.hayes.pvtsys.pojo.TestCase;
import com.hayes.pvtsys.pojo.TestResult;
import com.hayes.pvtsys.pojo.Ticket;
import com.hayes.pvtsys.query.BaseCaseQuery;
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

import static java.util.stream.Collectors.*;

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
        TestCase testCase;
        if (testCaseDto.getId() != null){
            testCase = ticketCaseRepository.findById(testCaseDto.getId()).orElseThrow();
        } else {
            testCase = new TestCase();
        }
        testCase.setTicketNo(testCaseDto.getTicketNo());
        testCase.setDescription(testCaseDto.getDescription());
        testCase.setSummary(testCaseDto.getSummary());
        testCase.setExpectedResult(testCaseDto.getExpectedResult());
        testCase.setStep(testCaseDto.getStep());
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
    
    public PageResponse<TestCase> queryPage(BaseCaseQuery query){
        Pageable pageRequest = PageRequest.of(query.getPageNum() - 1, query.getPageSize());
        Page<TestCase> testCases = ticketCaseRepository.queryPage(pageRequest, query);
        return new PageResponse<>(testCases);
    }

    public TestCase queryCaseDetail(Integer caseId){
        TestCase testCase = ticketCaseRepository.findById(caseId).orElseThrow();
        Set<Integer> categoryList = new HashSet<>();
        String rtFlow = splitCategory(caseId, categoryList);
        testCase.setCategory(categoryList);
        testCase.setRtFlow(rtFlow);
        return testCase;
    }

    /**
     *
     * base
     * @param query 查询条件
     * @return 分页记录
     */
    public PageResponse<BaseTestCase> findBaseCasePage(BaseCaseQuery query){
        Pageable pageRequest = PageRequest.of(query.getPageNum() - 1, query.getPageSize());

        Page<BaseTestCase> baseTestCases = baseTicketCaseRepository.findPage(pageRequest, query);
        return new PageResponse<>(baseTestCases);
    }

    public void addBaseCase(BaseTestCase baseTestCase){
        baseTestCase.setRowHeight(baseTestCase.rowHeight());
        baseTestCase.setCategoryVal(TestCategoryEnum.getEnvInt(baseTestCase.getCategory()));
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
                testCase = ticketCaseRepository.saveAndFlush(testCase);
                List<Integer> category = Arrays.stream(baseTestCase.getCategory().split(",")).map(Integer::parseInt).toList();
                ticketResultRepository.saveAllAndFlush(createResults(category, testCase, baseTestCase.getRtFlow()));
            }
        }
    }

    private List<TestResult> createResults(List<Integer> category, TestCase testCase, String rtFlow){
        Set<Integer> exist = new HashSet<>();
        splitCategory(testCase.getId(), exist);
        List<TestResult> results = new ArrayList<>();
        for (Integer e: category){
            if (!exist.contains(e)){
                TestResult result = new TestResult();
                result.setTestCase(testCase);
                result.setCategory(e);
                if ((e & TestEnvEnum.RT.getValue()) > 0){
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
        testCase.setStep(baseTestCase.getStep());
        testCase.setPriority(baseTestCase.getPriority());
        testCase.setType(baseTestCase.getType());
        testCase.setRowHeight(baseTestCase.getRowHeight());
        testCase.setBaseCaseFrom(baseTestCase.getId());
        return testCase;
    }
    private String splitCategory(Integer caseId, Set<Integer> sum){
        List<TestResult> results = ticketResultRepository.findTestResultByCaseId(caseId);
        String rtFlow = null;
        for (TestResult result : results){
            if ((result.getCategory() & TestEnvEnum.RT.getValue()) > 0){
                rtFlow = result.getRtFlow();
            }
            sum.add(result.getCategory());
        }
        return rtFlow;
    }
}
