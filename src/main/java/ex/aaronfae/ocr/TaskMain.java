package ex.aaronfae.ocr;

import ex.aaronfae.util.ExcelUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskMain {

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        List<Future<String>> results = new ArrayList<Future<String>>();

        File fileDir = new File("output-dataJPGAndScale");
//        File fileDir = new File("output-dataJPG");
        File[] files = fileDir.listFiles();
        long startTime = System.currentTimeMillis();
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            Future<String> result = fixedThreadPool.submit(new Task(file));
            results.add(result);
        }
        fixedThreadPool.shutdown();

        while (true) {
            if (fixedThreadPool.isTerminated()) {
                ExcelUtil.StringFutureListToExcelByPolish("output-dataJPGAndScale.xls", results);
//                ExcelUtil.StringFutureListToExcel("output-dataJPG.xls", results);
                System.out.println("运行时间：" + (System.currentTimeMillis() - startTime) / 1000);
                break;
            }
        }
    }

}
