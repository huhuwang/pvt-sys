package com.hayes.pvtsys.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.hayes.pvtsys.enums.Constants;
import com.hayes.pvtsys.enums.ExcelTitleEnum;
import com.hayes.pvtsys.enums.TestEnvEnum;
import com.hayes.pvtsys.enums.TestDeviceEnum;
import com.hayes.pvtsys.pojo.*;
import com.hayes.pvtsys.repository.DeploymentRepository;
import com.hayes.pvtsys.repository.TicketRepository;
import com.hayes.pvtsys.repository.TicketResultRepository;
import com.hayes.pvtsys.util.ServerPath;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;

@Service
public class DownloadService {

    @Autowired
    private TicketResultRepository ticketResultRepository;

    @Autowired
    private DeploymentRepository deploymentRepository;

    @Autowired
    private TicketRepository ticketRepository;

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
        List<Ticket> tickets = ticketRepository.findTicketByDeploymentIdOrderByCreateTimeDescIdDesc(deploymentId);
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
        List<Ticket> tickets = ticketRepository.findTicketByDeploymentIdOrderByCreateTimeDescIdDesc(deploymentId);

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
    
    @Data
    private static class Title{
        private String name;
        
        private Integer width;
        
        private String key;
    }
}
