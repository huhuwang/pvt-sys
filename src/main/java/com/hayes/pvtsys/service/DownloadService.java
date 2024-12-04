package com.hayes.pvtsys.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.hayes.pvtsys.enums.Constants;
import com.hayes.pvtsys.enums.ExcelTitleEnum;
import com.hayes.pvtsys.enums.TestDeviceEnum;
import com.hayes.pvtsys.enums.TestEnvEnum;
import com.hayes.pvtsys.pojo.Document;
import com.hayes.pvtsys.pojo.*;
import com.hayes.pvtsys.repository.DeploymentRepository;
import com.hayes.pvtsys.repository.TicketRepository;
import com.hayes.pvtsys.repository.TicketResultRepository;
import com.hayes.pvtsys.util.ServerPath;
import jakarta.persistence.Column;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DownloadService {

    @Autowired
    private TicketResultRepository ticketResultRepository;

    @Autowired
    private DeploymentRepository deploymentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    private final String elasticUrl = "https://baidu.com/";

    private final String titleColor = "80aaff";


    public void downloadSU(HttpServletResponse response, String ticketNO, int env){
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        TestDeviceEnum[] devices = TestDeviceEnum.values();
        try(XSSFWorkbook workbook = new XSSFWorkbook()){
            CellStyle style = buildCellStyleFont(workbook);

            for (TestDeviceEnum device: devices){
                XSSFSheet sheet = workbook.createSheet(device.name());
                //excel title
                createTitle(workbook, sheet, env);

                //数据
                List<TestResult> testResultWithDevice = ticketResultRepository.findTestResultByEnvAndTicket(ticketNO, env + device.getValue());
                for (int i = 0; i < testResultWithDevice.size(); i++){
                    int rowIndex = i + 1;
                    TestResult result = testResultWithDevice.get(i);
                    TestCase testCase = result.getTestCase();
                    int height = testCase.getRowHeight();
                    Row rowData = sheet.createRow(rowIndex);
                    int min = TestDeviceEnum.WEB == device? 15: 20;
                    rowData.setHeight((short) (Math.max(height,min) * 300));

                    Cell cell0 = rowData.createCell(0);
                    cell0.setCellStyle(style);
                    cell0.setCellValue(rowIndex);

                    Cell cell1 = rowData.createCell(1);
                    cell1.setCellStyle(style);
                    cell1.setCellValue(testCase.getDescription());

                    Cell cell2 = rowData.createCell(2);
                    cell2.setCellStyle(style);
                    cell2.setCellValue(testCase.getStep());


                    Cell cell3 = rowData.createCell(3);
                    cell3.setCellStyle(style);
                    cell3.setCellValue(result.getTestData());

                    Cell cell4 = rowData.createCell(4);
                    cell4.setCellStyle(style);
                    cell4.setCellValue(testCase.getExpectedResult());

                    Byte pass = result.getResult();
                    Cell cell5 = rowData.createCell(5);
                    CellStyle color = buildCellStyleBackGround(workbook, pass == null ? null: pass == 1 ? "green" : pass == 2 ? "red" : null);
                    cell5.setCellStyle(color);
                    cell5.setCellValue(formatPass(result.getResult()));

                    List<Document> evidences = result.getDocuments();
                    for (int j = 0; j < evidences.size(); j ++){
                        Document document = evidences.get(j);
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(ServerPath.partTomcat() + document.getUrl()))
                                .build();
                        int finalJ = j + 6;
                        client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                                .thenApply(HttpResponse::body).thenAccept( e -> insertPic(e, workbook, sheet, rowIndex, finalJ)).join();
                    }
                }
            }
            writeToBrose(response, workbook, ticketNO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void downloadRT(HttpServletResponse response, Integer deploymentId){
        Deployment deployment = deploymentRepository.findById(deploymentId).orElseThrow();
        List<Ticket> tickets = ticketRepository.findTicketByDeploymentId(deploymentId);
        List<String> ticketNos = tickets.stream().map(Ticket::getTicketNo).toList();
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

        RTTemplate rtTemplate = deployment.getRtTemplate();
        Integer flowNumber = rtTemplate.getFlowNumber();
        try(XSSFWorkbook workbook = new XSSFWorkbook()){
            //first sheet
            createFirstSheet(workbook, rtTemplate);
            CellStyle style = buildCellStyleFont(workbook);
            //case
            for (int num = 0; num < flowNumber; num++){
                String RT = "RT" + (num + 1);
                XSSFSheet sheet = workbook.createSheet(RT);
                createTitle(workbook, sheet, TestEnvEnum.RT.getValue());

                List<TestResult> testResult= ticketResultRepository.findTestResultByEnvAndTicketWithRT(ticketNos, RT);
                for (int i = 0; i < testResult.size(); i++) {
                    int rowIndex = i + 1;
                    TestResult result = testResult.get(i);
                    TestCase testCase = result.getTestCase();
                    int height = testCase.getRowHeight();
                    Row rowData = sheet.createRow(rowIndex);
                    int min = (result.getCategory() & TestDeviceEnum.WEB.getValue()) > 0 ? 15: 20;
                    short leastHeight = (short) (Math.max(height, min) * 300);
                    rowData.setHeight(leastHeight);

                    Cell cell0 = rowData.createCell(0);
                    cell0.setCellStyle(style);
                    cell0.setCellValue(rowIndex);

                    Cell cell1 = rowData.createCell(1);
                    cell1.setCellStyle(style);
                    cell1.setCellValue(testCase.getSummary());

                    Cell cell2 = rowData.createCell(2);
                    cell2.setCellStyle(style);
                    cell2.setCellValue(testCase.getDescription());

                    rowData.createCell(3);
                    rowData.createCell(4);
                    Cell cell5 = rowData.createCell(5);
                    cell5.setCellValue(formatPriority(testCase.getPriority()));
                    rowData.createCell(6);
                    rowData.createCell(7);

                    Cell cell8 = rowData.createCell(8);
                    cell8.setCellStyle(style);
                    cell8.setCellValue(testCase.getStep());

                    Cell cell9 = rowData.createCell(9);
                    cell9.setCellStyle(style);
                    cell9.setCellValue(testCase.getExpectedResult());

                    Cell cell10 = rowData.createCell(10);
                    cell10.setCellStyle(style);
                    cell10.setCellValue(result.getTestData());


                    Byte pass = result.getResult();
                    Cell cell11 = rowData.createCell(11);
                    CellStyle color = buildCellStyleBackGround(workbook, pass == null ? null: pass == 1 ? "green" : pass == 2 ? "red" : null);
                    cell11.setCellStyle(color);
                    cell11.setCellValue(formatPass(result.getResult()));

                    Cell cell12 = rowData.createCell(12);
                    cell12.setCellStyle(style);
                    cell12.setCellValue(result.getRemark());

                    List<Document> evidences = result.getDocuments();
                    for (int j = 0; j < evidences.size(); j ++){
                        Document document = evidences.get(j);
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(ServerPath.partTomcat() + document.getUrl()))
                                .build();
                        int finalJ = j + 13;
                        client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                                .thenApply(HttpResponse::body).thenAccept( e -> insertPic(e, workbook, sheet, rowIndex, finalJ)).join();
                    }

                }
            }
            writeToBrose(response, workbook, "RT-" + deployment.getApplicationName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void downloadPVT(HttpServletResponse response, Integer deploymentId){

        Deployment deployment = deploymentRepository.findById(deploymentId).orElseThrow();
        List<Ticket> tickets = ticketRepository.findTicketByDeploymentId(deploymentId);
        List<String> ticketNos = tickets.stream().map(Ticket::getTicketNo).toList();
        List<TestResult> testResult= ticketResultRepository.findTestResultByEnvAndTicketWithPVT(ticketNos);

        String attachmentFileName = deployment.getApplicationName() + "-PVT.docx";

        try (InputStream existDoc = new ClassPathResource("pvt/" + attachmentFileName).getInputStream();
             XWPFDocument document = new XWPFDocument(existDoc)){

            //开头ticket list
            addContentAtStart(document, tickets, deployment);

            //中间ticket check
            List<String> NOUsingTicket = addResult(document, testResult, tickets);

            //末尾无法check的备注
            addContentAtEnd(document, NOUsingTicket);


            writeDocToBrose(response, document, "PVT-" + deployment.getApplicationName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void addContentAtStart(XWPFDocument document,  List<Ticket> tickets, Deployment deployment){
        //story 相关的case
        List<Ticket> storyTickets = tickets.stream().filter(e -> e.getType() == (byte) 1).toList();
        Map<String, List<Ticket>> map = convertListToMapWithTypeKey(storyTickets);

        XWPFParagraph paragraph_2 = document.getParagraphs().get(1);
            map.forEach((key, value) -> {
                XWPFRun run_2 = paragraph_2.createRun();
                run_2.addCarriageReturn();
                run_2.setText(key);
                run_2.addCarriageReturn();
                for (Ticket ticket: value){
                    XWPFHyperlinkRun hyperlinkRun = paragraph_2.createHyperlinkRun(ticket.getTicketUrl());
                    hyperlinkRun.setText(ticket.getTicketNo());
                    hyperlinkRun.setColor("1a65ff");
                    XWPFRun run_2_2 = paragraph_2.createRun();
                    run_2_2.setText("     " + ticket.getTicketTitle());
                    run_2_2.addCarriageReturn();
                }
            });

            XWPFParagraph paragraph_3 = document.getParagraphs().get(2);
            XWPFRun run_3 = paragraph_3.createRun();
            run_3.setText("Release Green Time");
            run_3.addCarriageReturn();
            Date deploymentDate = deployment.getDeploymentDate();
            String formatDate = DateUtil.formatDate(deploymentDate) + " 22:00";
            Date startDate = DateUtil.parse(formatDate);
            Date endDate = DateUtil.offsetHour(startDate, 4);
            String newDateStr = DateUtil.format(startDate, "HH:mm dd/MM/yyyy") + " - " + DateUtil.format(endDate, "HH:mm dd/MM/yyyy");
            run_3.setText(newDateStr);
            run_3.addCarriageReturn();

            //part 2
            XWPFParagraph paragraph_4 = document.getParagraphs().get(3);
            XWPFRun run_4 = paragraph_4.createRun();
            run_4.setText("Part II: Step for having PVT");//标题名称
            run_4.setColor(titleColor);//标题颜色
            run_4.setFontSize(16);
            run_4.addCarriageReturn();//回车换行
            XWPFRun run_4_1 = paragraph_4.createRun();
            run_4_1.setText("1.Rundown of PVT");

            Date startDateHealth = DateUtil.offsetMinute(startDate, 30);
            Date endDateHealth = DateUtil.offsetMinute(startDate, 45);

            Date startDatePVT = DateUtil.offsetMinute(startDate, 120);
            Date endDateHPVT= DateUtil.offsetMinute(startDate, 240);

            String[][] dataRundown = {
                    {"Items", "DateTime", "Party(ies)", "Remarks"},
                    {"Deployment", DateUtil.format(startDate, "HH:mm dd/MM/yyyy"), "Teach Team", ""},
                    {"Health check", DateUtil.format(startDateHealth, "HH:mm dd/MM/yyyy") + " - " + DateUtil.format(endDateHealth, "HH:mm dd/MM/yyyy"), "REMARK TODO"},
                    {"PVT(IT + Biz)", DateUtil.format(startDatePVT, "HH:mm dd/MM/yyyy") + " - " + DateUtil.format(endDateHPVT, "HH:mm dd/MM/yyyy"), "REMARK TODO"},
            };
            buildDocTab(document, dataRundown);
    }

    private List<String> addResult(XWPFDocument document, List<TestResult> testResult, List<Ticket> tickets){

        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

        List<Ticket> storyTickets = tickets.stream().filter(e -> e.getType() == (byte) 1).toList();
        Map<String, Ticket> storyTicketMap = storyTickets.stream().collect(Collectors.toMap(Ticket::getTicketNo, Function.identity(), (e1, e2) -> e1));

        List<String> noUseTickets = new ArrayList<>(storyTickets.stream().map(Ticket::getTicketNo).toList());
        XWPFTable table = document.getTables().get(2);
        table.setTableAlignment(TableRowAlign.LEFT);


        testResult.sort((o1, o2) -> {
            if (o1.getTestCase().getType() > o2.getTestCase().getType()){
                return 1;
            } else {
                return o1.getId() - o2.getId();
            }
        });
        List<ResultDto> resultDtoList = convertTestResultToResultDto(testResult);

        for (ResultDto result : resultDtoList) {
            XWPFTableRow tableRow = table.createRow();
            TestCase testCase = result.getTestCase();
            String ticketNo = testCase.getTicketNo();
            if (ticketNo.contains("COMMON")) {
                tableRow.getCell(0).setText("Regression Check");
                tableRow.getCell(1).setText(testCase.getSummary());
            } else {
                XWPFHyperlinkRun hyperlinkRun = tableRow.getCell(0).addParagraph().createHyperlinkRun(storyTicketMap.get(testCase.getTicketNo()).getTicketUrl());
                hyperlinkRun.setText(ticketNo);
                noUseTickets.remove(ticketNo);
                tableRow.getCell(1).setText(storyTicketMap.get(testCase.getTicketNo()).getTicketTitle());
            }
            String step = testCase.getStep() == null? "": testCase.getStep();
            String[] steps = step.split("\n");
            XWPFParagraph paragraphStep = tableRow.getCell(2).addParagraph();
            paragraphStep.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun runStep = paragraphStep.createRun();
            for (String st: steps){
                if (StrUtil.isNotBlank(st) && st.trim().startsWith("pvt-source")){
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(ServerPath.partTomcat() + st))
                            .build();
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                            .thenApply(HttpResponse::body)
                            .thenAccept(e -> {
                                try {
                                    runStep.addPicture(e, XWPFDocument.PICTURE_TYPE_JPEG, "health.jpg", Units.toEMU(100), Units.toEMU(50));
                                } catch (InvalidFormatException | IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }).join();
                    runStep.addBreak();
                } else {
                    runStep.setText(st);
                    runStep.addBreak();
                }
            }
            tableRow.getCell(3).setText(result.getTestData());
            XWPFParagraph paragraphER= tableRow.getCell(4).addParagraph();
            paragraphER.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun runER = paragraphER .createRun();

            String expectedResult = testCase.getExpectedResult()== null? "": testCase.getExpectedResult();
            String[] ers = expectedResult.split("\n");
            for (String er: ers){
                runER.setText(er);
                runER.addBreak();
            }
            Byte pass = result.getResult();
            String rs = pass == null ? null: pass == 1 ? "PASS" : pass == 2 ? "NO" : null;
            tableRow.getCell(5).setText(rs);

            XWPFParagraph paragraphEvidence= tableRow.getCell(6).addParagraph();
            paragraphEvidence.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun evidenceRun = paragraphEvidence.createRun();
            List<Document> webDocuments = result.getWebDocuments();
            List<Document> ipadDocuments = result.getIpadDocuments();
            createPVTEvidence(client,evidenceRun,webDocuments, "Web:");
            createPVTEvidence(client,evidenceRun,ipadDocuments, "Ipad:");

        }
        return noUseTickets;
    }

    private void addContentAtEnd(XWPFDocument document,  List<String> tickets){
        if (CollUtil.isNotEmpty(tickets)){
            XWPFTable table = document.createTable(tickets.size() + 1, 2);
            table.getRow(0).getCell(0).setText("Category(Jira)");
            table.getRow(0).getCell(0).setWidth("2000");
            table.getRow(0).getCell(1).setText("Remark");
            table.getRow(0).getCell(1).setWidth("8000");

            for (int i = 0; i < tickets.size(); i++){
                table.getRow(i + 1).getCell(0).setText(tickets.get(i));
            }
        }

    }
    private void buildDocTab(XWPFDocument document,  String[][] data){
        XWPFTable table= document.getTables().get(0);
        table.setTableAlignment(TableRowAlign.LEFT);
        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[i].length; j++){
                table.getRow(i).getCell(j).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                table.getRow(i).getCell(j).setText(data[i][j]);
            }
        }
    }

    private void createFirstSheet(XSSFWorkbook workbook, RTTemplate rtTemplate){
        XSSFSheet sheet = workbook.createSheet(rtTemplate.getTemplateName());
        int flowNumber = rtTemplate.getFlowNumber();
        List<KVConstants> kvConstants = rtTemplate.getKvConstants();

        //first row
        Row rowTitle = sheet.createRow(0);
        List<Title> titles = getTitles(flowNumber);
        for (int i = 0; i < titles.size(); i++){
            Title title = titles.get(i);
            sheet.setColumnWidth(i, title.getWidth());
            Cell cellTitle = rowTitle.createCell(i);
            cellTitle.setCellStyle(buildCellStyleBackGround(workbook, "blue"));
            cellTitle.setCellValue(title.getName());
        }

        if (CollUtil.isNotEmpty(kvConstants)){
            Map<Integer, Map<String,String>> allData = new LinkedHashMap<>();
            for (KVConstants kv : kvConstants){
                Integer parentId = kv.getParentId();
                Integer id = kv.getId();
                String keyName = kv.getKeyName();
                String keyValue = kv.getKeyValue();
                if (Constants.KV_MAIN_NAME.equalsIgnoreCase(keyName) && parentId == null){
                    createFlowData(allData, id, keyName, keyValue);
                } else {
                    createFlowData(allData, parentId, keyName, keyValue);
                }
            }
            List<Map<String,String>> dataAll = new ArrayList<>(allData.values());
            CellStyle style = buildCellStyleFont(workbook);
            for (int rowLen = 0; rowLen < dataAll.size(); rowLen ++){
                int rowIndex = rowLen + 1;
                Row rowData = sheet.createRow(rowIndex);
                Map<String, String> data = dataAll.get(rowLen);
                rowData.setHeight((short) 300);
                for (int colLen = 0; colLen < titles.size(); colLen++){
                    Title title = titles.get(colLen);
                    Cell cell = rowData.createCell(colLen);
                    cell.setCellStyle(style);
                    cell.setCellValue(data.get(title.getKey()));
                }
            }
        }
    }


    private void createTitle(XSSFWorkbook workbook, XSSFSheet sheet, int env){
        Row rowTitle = sheet.createRow(0);
        List<ExcelTitleEnum> titles = ExcelTitleEnum.allEnv(env);
        for (int i = 0; i < titles.size(); i++){
            ExcelTitleEnum title = titles.get(i);
            sheet.setColumnWidth(i, title.getWidth());
            Cell cellTitle = rowTitle.createCell(i);
            cellTitle.setCellStyle(buildCellStyleBackGround(workbook, "blue"));
            cellTitle.setCellValue(title.getName());
        }
    }

    private List<Title> getTitles(int flowNumber) {
        List<Title> titles = new ArrayList<>();
        Title titleFlowPoint = new Title();
        titleFlowPoint.setName("End-to-end flow with LOU application");
        titleFlowPoint.setWidth(10000);
        titleFlowPoint.setKey(Constants.KV_MAIN_NAME);
        titles.add(titleFlowPoint);
        for (int i = 1; i <= flowNumber; i++){
            Title title = new Title();
            title.setName("Application" + i);
            title.setWidth(8000);
            title.setKey("RT" + i);
            titles.add(title);
        }
        return titles;
    }

    private void writeToBrose(HttpServletResponse response, XSSFWorkbook workbook, String fileName){
        try(OutputStream os = new BufferedOutputStream(response.getOutputStream())){
            response.reset();
            String filename = fileName + ".xlsx";
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            workbook.write(os);
            os.flush();
        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private void writeDocToBrose(HttpServletResponse response, XWPFDocument document, String fileName){
        try(OutputStream os = new BufferedOutputStream(response.getOutputStream())){
            response.reset();
            String filename = fileName + ".docx";
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setContentType("application/octet-stream");
            document.write(os);
            os.flush();
        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void insertPic(InputStream is, XSSFWorkbook workbook, XSSFSheet sheet, int row, int col){
        try {
            int pictureIdx = workbook.addPicture(is, Workbook.PICTURE_TYPE_JPEG);
            Drawing<?> drawing = sheet.createDrawingPatriarch();
            sheet.setColumnWidth(col, 18000);
            ClientAnchor anchor = new XSSFClientAnchor(0,0,0,0,col, row, col + 1, row + 1);
            Picture picture = drawing.createPicture(anchor, pictureIdx);
            picture.resize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void  createFlowData(Map<Integer, Map<String,String>> res, Integer id, String k, String v){
        Map<String, String> dataDetail;
        if (!res.containsKey(id)){
            dataDetail = new HashMap<>();
        } else {
            dataDetail = res.get(id);
        }
        dataDetail.put(k, v);
        res.put(id, dataDetail);
    }

    private CellStyle buildCellStyleBackGround(XSSFWorkbook workbook, String colorSetting){
        CellStyle style = workbook.createCellStyle();
        if (StrUtil.isNotBlank(colorSetting)){
            XSSFColor color = new XSSFColor();
            if ("green".equalsIgnoreCase(colorSetting)){
                color.setRGB(new byte[]{0, (byte)255,0});
            } else if ("red".equalsIgnoreCase(colorSetting)){
                color.setRGB(new byte[]{(byte)255, 0,0});
            } else if ("blue".equalsIgnoreCase(colorSetting)){
                color.setRGB(new byte[]{(byte)26, (byte)198, (byte)255});
            }
            style.setFillForegroundColor(color);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        return style;
    }

    private CellStyle buildCellStyleFont(XSSFWorkbook workbook){
        //单元格样式和字体
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        return style;
    }
    private String formatPass(Byte pass){
        if (pass != null){
            if (pass == 1){
                return "PASS";
            } else if (pass == 2){
                return "NO PASS";
            }
        }
        return null;
    }

    private String formatPriority(int priority){
        return switch (priority) {
            case 1 -> "Urgent";
            case 2 -> "Highest";
            case 3 -> "High";
            case 4 -> "Low";
            case 5 -> "Lowest";
            case 6 -> "Blocker";
            case 7 -> "Critical";
            case 8 -> "Major";
            case 9 -> "Minor";
            case 10 -> "Trivial";
            default -> null;
        };
    }

    private List<ResultDto> convertTestResultToResultDto(List<TestResult> testResult){
        Map<Integer, ResultDto> resultDtoMap = new LinkedHashMap<>();
        for (TestResult result: testResult){
            ResultDto resultDto;
            if (resultDtoMap.containsKey(result.getTestCase().getId())){
                resultDto = resultDtoMap.get(result.getTestCase().getId());
            } else {
                resultDto = new ResultDto();
                resultDto.setTestCase(result.getTestCase());
                resultDto.setActualResult(result.getActualResult());
                resultDto.setResult(result.getResult());
                resultDto.setTestData(result.getTestData());
            }
            Integer category = result.getCategory();
            if ((category & TestDeviceEnum.WEB.getValue()) > 0){
                resultDto.setWebDocuments(result.getDocuments());
            } else if ((category & TestDeviceEnum.IPAD.getValue()) > 0) {
                resultDto.setIpadDocuments(result.getDocuments());
            }
            resultDtoMap.put(result.getTestCase().getId(), resultDto);
        }
        return new ArrayList<>(resultDtoMap.values());
    }

    private void createPVTEvidence(HttpClient client, XWPFRun evidenceRun, List<Document> documents, String device){
        if (CollUtil.isNotEmpty(documents)){
            evidenceRun.setText(device);
            evidenceRun.addBreak();
            for (Document e: documents){
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(ServerPath.partTomcat() + e.getUrl()))
                        .build();
                client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                        .thenApply(HttpResponse::body)
                        .thenAccept(stream -> {
                            try {
                                evidenceRun.addPicture(stream , XWPFDocument.PICTURE_TYPE_JPEG, "evidence.jpg", Units.toEMU(100), Units.toEMU(50));
                            } catch (InvalidFormatException | IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }).join();
                evidenceRun.addBreak();
            }
        }
    }
    private Map<String, List<Ticket>> convertListToMapWithTypeKey(List<Ticket> tickets){
        Map<String, List<Ticket>> map = new HashMap<>();
        for (Ticket ticket: tickets){
            String ticketType = ticket.getTicketType();
            List<Ticket> ticketsList;
            if (map.containsKey(ticketType)){
                ticketsList = map.get(ticketType);
            } else {
                ticketsList = new ArrayList<>();
            }
            ticketsList.add(ticket);
            map.put(ticketType, ticketsList);
        }
        return map;
    }
    
    @Data
    private static class Title{
        private String name;
        
        private Integer width;
        
        private String key;
    }

    @Data
    private static class ResultDto{

        private TestCase testCase;

        private String testData;

        private String actualResult;

        private Byte result;

        private List<Document> webDocuments;

        private List<Document> ipadDocuments;
    }
}
