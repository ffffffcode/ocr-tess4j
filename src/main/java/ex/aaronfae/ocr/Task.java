package ex.aaronfae.ocr;

import ex.aaronfae.util.ImageUtil;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.Callable;

public class Task implements Callable<String> {

    private File file;

    public Task(File file) {
        this.file = file;
    }

    @Override
    public String call() {
        ITesseract instance = new Tesseract();
        instance.setLanguage("chi_sim");
        try {
            BufferedImage image = ImageIO.read(file).getSubimage(0, 0, 520, 80);
            BufferedImage target = ImageUtil.filter(image);
            String content = instance.doOCR(target);
            return content;
        } catch (Exception e) {
            System.out.println("无法识别：" + file.getName());
            return "";
        }
    }

}
