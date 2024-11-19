package com.hayes.pvtsys.service;

import com.hayes.pvtsys.enums.ExcelTitleEnum;
import com.hayes.pvtsys.enums.TestDeviceEnum;
import com.hayes.pvtsys.pojo.Document;
import com.hayes.pvtsys.pojo.TestCase;
import com.hayes.pvtsys.pojo.TestResult;
import com.hayes.pvtsys.query.DownloadQuery;
import com.hayes.pvtsys.repository.TicketResultRepository;
import com.hayes.pvtsys.util.ServerPath;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
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
import java.util.List;

@Service
public class DownloadService {

    @Autowired
    private TicketResultRepository ticketResultRepository;

    public void downloadSU(HttpServletResponse response, String ticketNO, int env){
//        String ticketNO = query.getTicketNo();
//        Integer evn = query.getEnv();
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        TestDeviceEnum[] devices = TestDeviceEnum.values();
        try(XSSFWorkbook workbook = new XSSFWorkbook()){
            for (TestDeviceEnum device: devices){
                XSSFSheet sheet = workbook.createSheet(device.name());
                //单元格样式和字体
                CellStyle style = workbook.createCellStyle();
                style.setWrapText(true);
                style.setVerticalAlignment(VerticalAlignment.CENTER);
                Font font = workbook.createFont();
                font.setFontHeightInPoints((short) 12);
                style.setFont(font);

                //excel title
                Row rowTitle = sheet.createRow(0);
                List<ExcelTitleEnum> titles = ExcelTitleEnum.allEnv(env);
                for (int i = 0; i < titles.size(); i++){
                    ExcelTitleEnum title = titles.get(i);
                    sheet.setColumnWidth(i, title.getWidth());
                    Cell cellTitle = rowTitle.createCell(i);
                    cellTitle.setCellStyle(buildCellStyleBackGround(workbook, "blue"));
                    cellTitle.setCellValue(title.getName());
                }
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
                    cell2.setCellValue(result.getStep());


                    Cell cell3 = rowData.createCell(3);
                    cell3.setCellStyle(style);
                    cell3.setCellValue(result.getTestData());

                    Cell cell4 = rowData.createCell(4);
                    cell4.setCellStyle(style);
                    cell4.setCellValue(testCase.getExpectedResult());

                    int pass = result.getResult();
                    Cell cell5 = rowData.createCell(5);
                    CellStyle color = buildCellStyleBackGround(workbook, pass == 1 ? "green" : pass == 2 ? "red" : null);
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

    private void writeToBrose(HttpServletResponse response, XSSFWorkbook workbook, String ticketNo){
        try(OutputStream os = new BufferedOutputStream(response.getOutputStream())){
            response.reset();
            String filename = ticketNo + ".xlsx";
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
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


    private CellStyle buildCellStyleBackGround(XSSFWorkbook workbook, String colorSetting){
        CellStyle style = workbook.createCellStyle();
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
        return style;
    }

    private String formatPass(int pass){
        if (pass == 1){
            return "PASS";
        } else if (pass == 2){
            return "NO PASS";
        }
        return null;
    }
}
