package ex.aaronfae.ocr;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OutputMain {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        File fileDir = new File("test-data");
        File[] files = fileDir.listFiles();
        long startTime = System.currentTimeMillis();
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            fixedThreadPool.submit(new Output(file));
        }
        fixedThreadPool.shutdown();

        while (true) {
            if (fixedThreadPool.isTerminated()) {
                System.out.println("运行时间：" + (System.currentTimeMillis() - startTime) / 1000);
                break;
            }
        }
    }
}
