package ia;

import com.googlecode.fannj.Fann;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by home on 02/11/2015.
 */
public class AI {

    private String mCharLib;
    private Fann mFann;

    public AI(String path, String charlib) {
        System.setProperty("jna.library.path", "C:\\Users\\home\\Desktop\\OCR-NN\\mylib\\FANN-2.2.0-Source\\bin");
        mCharLib = charlib;

        mFann = new Fann(path);
    }

    public Character check(String path) {
        float[] results = mFann.run(getPixelArray(path));

        float maxMatchValue = -1;
        boolean isInit = false;
        int maxPos = 0;

        for (int i = 0; i < results.length; i++) {
            if (!isInit) {
                maxMatchValue = results[i];
                isInit = true;
                maxPos = i;
            } else if (results[i] > maxMatchValue) {
                maxMatchValue = results[i];
                maxPos = i;
            }
        }
        return mCharLib.charAt(maxPos);
    }

    public static float[] getPixelArray(String path) {
        File file = new File(path);

        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        float[] array = new float[image.getWidth() * image.getHeight()];

        int i = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                array[i] = (image.getRGB(x, y) == -1 ? 0 : 1);
                i++;
            }
        }

        return array;
    }
}
