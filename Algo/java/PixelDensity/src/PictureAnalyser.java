import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by nicol on 03/10/2015.
 */
public class PictureAnalyser {

    private BufferedImage mBufferedImage;

    private ArrayList<ArrayList<Integer>> mMap = null;

    public PictureAnalyser(String filename) throws FileNotFoundException {
        File imgPath = new File(filename);
        try {
            mBufferedImage = ImageIO.read(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotFoundException();
        }
        getSimplifiedArray();
    }

    public int getWidth() {
        return mBufferedImage.getWidth();
    }

    public int getHeight() {
        return mBufferedImage.getHeight();
    }

    public ArrayList<ArrayList<Integer>> getSimplifiedArray() {
        if (mMap != null) {
            return mMap;
        }

        mMap = new ArrayList<ArrayList<Integer>>();
        for (int y = 0; y < getHeight(); y++) {
            ArrayList line = new ArrayList();
            mMap.add(y, line);
            for (int x = 0; x < getWidth(); x++) {

                int clr = mBufferedImage.getRGB(x, y);
                int  r   = (clr & 0x00ff0000) >> 16;
                int  g = (clr & 0x0000ff00) >> 8;
                int  b  =  clr & 0x000000ff;

                int pixelId = -1;
                if (r + g + b != 0) {
                    pixelId = 0;
                } else {
                    pixelId = 1;
                }

                mMap.get(y).add(x, pixelId);
            }
        }
        return mMap;
    }

    public ArrayList<Integer> getHorizontalPixelDensity() {
        ArrayList<Integer> pixelD = new ArrayList<>();
        for (ArrayList<Integer> line : mMap) {
            int cpt = 0;
            for (Integer pixel : line) {
                if (pixel == 1)
                    cpt++;
            }
            pixelD.add(cpt);
        }
        return pixelD;
    }

    public ArrayList<Integer> getVerticalPixelDensity() {
        ArrayList<Integer> pixelD = new ArrayList<>();
        for (int count = 0; count < mMap.get(0).size(); count++) {
            int cpt = 0;
            for (ArrayList<Integer> line : mMap) {
                if (line.get(count) == 1) {
                    cpt++;
                }
            }
            pixelD.add(cpt);
        }
        return pixelD;
    }

}
