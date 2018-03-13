package ex.aaronfae.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ExcelUtil {

    public static void StringFutureListToExcel(String file, List<Future<String>> results) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        sheet.setDefaultColumnWidth((short) 30);
        HSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("企业名称");
        row0.createCell(1).setCellValue("企业注册号");
        for (int x = 1; x <= results.size(); x++) {
            String content = "";
            try {
                content = results.get(x - 1).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            HSSFRow row = sheet.createRow(x);
            row.createCell(0).setCellValue(getName(content).trim());
            row.createCell(1).setCellValue(getNum(content).trim());
        }

        File xlsFile = new File(file);
        try {
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
            workbook.close();
            xlsStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getName(String content) {
        if (null == content || "".equals(content)) {
            return " ";
        }
        if (content.lastIndexOf(":") == -1) {
            return " ";
        }
        String name = content.substring(content.lastIndexOf(":") + 1);
        name = name.replace("眼", "限");
        return name;
    }

    private static String getNum(String content) {
        if (null == content || "".equals(content)) {
            return " ";
        }
        if (content.lastIndexOf(":") == -1 || content.lastIndexOf("企") == -1) {
            return " ";
        }
        String num = content.substring(content.indexOf(":") + 1, content.lastIndexOf("企") - 1);
        return num;
    }

    public static void StringFutureListToExcelByPolish(String file, List<Future<String>> results) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        sheet.setDefaultColumnWidth((short) 30);
        HSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("企业名称");
        row0.createCell(1).setCellValue("企业注册号");
        for (int x = 1; x <= results.size(); x++) {
            String content = "";
            try {
                content = results.get(x - 1).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            HSSFRow row = sheet.createRow(x);
            row.createCell(0).setCellValue(polishGetName(content));
            row.createCell(1).setCellValue(polishGetNum(content));
        }

        File xlsFile = new File(file);
        try {
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
            workbook.close();
            xlsStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String polishGetName(String rowContent) {
        StringBuffer content = new StringBuffer(rowContent);
        if (null == content || "".equals(content) || " ".equals(content)) {
            return " ";
        }
        if (content.lastIndexOf(":") == -1) {
            return " ";
        }
        return content.substring(content.lastIndexOf("企") + 5).replace("二", "").replace("翦艮", "限").replace("眼", "限").trim();
    }

    private static String polishGetNum(String rowContent) {
        StringBuffer content = new StringBuffer(rowContent);
        if (null == content || "".equals(content) || " ".equals(content)) {
            return " ";
        }
        if (content.lastIndexOf(":") == -1 || content.lastIndexOf("企") == -1) {
            return " ";
        }
        return content.substring(content.indexOf(":") + 2, content.lastIndexOf("企") - 1);
    }

}
