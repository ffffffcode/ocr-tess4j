package ex.aaronfae.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SingleOCR {

    public static void main(String[] args) {
        File imageFile = new File("test-data/test2.jpg");
        ITesseract instance = new Tesseract();
        instance.setLanguage("chi_sim");
        try {
            BufferedImage image = ImageIO.read(imageFile).getSubimage(0, 0, 520, 80);
            String result = instance.doOCR(image);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
