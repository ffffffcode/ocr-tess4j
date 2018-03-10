package ex.aaronfae.util;

import com.jhlbas.image.DiffusionFilter;
import com.jhlbas.image.MapColorsFilter;
import net.sourceforge.tess4j.util.ImageHelper;

import java.awt.image.BufferedImage;

public class ImageUtil {

    public static BufferedImage filter(BufferedImage src) {
        DiffusionFilter diffusionFilter = new DiffusionFilter();
        MapColorsFilter mapColorsFilter = new MapColorsFilter();
        return mapColorsFilter.filter(diffusionFilter.filter(src, null), null);
    }

    public static BufferedImage toBinary(BufferedImage src) {
        return ImageHelper.convertImageToBinary(src);
    }

    public static BufferedImage toGray(BufferedImage src) {
        return ImageHelper.convertImageToGrayscale(src);
    }

}
