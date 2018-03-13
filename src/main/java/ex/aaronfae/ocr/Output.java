package ex.aaronfae.ocr;

import ex.aaronfae.util.ImageUtil;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Output implements Runnable {

    private File file;

    public Output(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        ITesseract instance = new Tesseract();
        instance.setLanguage("chi_sim");
        try {
            BufferedImage image = ImageIO.read(file).getSubimage(0, 0, 520, 80);
            BufferedImage target = ImageUtil.filter(image);
            File targetDir = new File("output-dataJPG");
            if (!targetDir.exists()) {
                targetDir.mkdir();
            }
            ImageIO.write(target, "jpg", new File(targetDir, file.getName().replace("png", "jpg")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
