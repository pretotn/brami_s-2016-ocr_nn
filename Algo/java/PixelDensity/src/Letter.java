import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by nicol on 03/10/2015.
 */
public class Letter {

    private PictureAnalyser mAnalyser;

    private ArrayList<Integer> mCurveH;
    private ArrayList<Integer> mCurveV;

    private String mName;

    public Letter(String filename) throws FileNotFoundException {
        mName = filename;

        try {
            mAnalyser = new PictureAnalyser(filename);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }

        mCurveH = mAnalyser.getHorizontalPixelDensity();
        mCurveV = mAnalyser.getVerticalPixelDensity();
    }

    public ArrayList<Integer> getHorizontalCurve() {
        return mCurveH;
    }

    public ArrayList<Integer> getVerticalCurve() {
        return mCurveV;
    }

    public String getName() {
        return mName;
    }
}
