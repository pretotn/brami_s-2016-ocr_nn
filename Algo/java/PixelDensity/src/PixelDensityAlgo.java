import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by home on 13/10/2015.
 */
public class PixelDensityAlgo {

    static LogFrame mLog = new LogFrame();

    public static void run(String inputPath) {

        PixelDensityAlgo algo = new PixelDensityAlgo(inputPath);

    }

    // Variable to store the source picture
    public Letter mSource;

    // Variable
    public ArrayList<Letter> mLib;

    public PixelDensityAlgo(String inputPath) {
        mLog.add("Starting PixelDensity recognition algorithm");

        mLib = new ArrayList<>();

        LoadFiles(inputPath);
    }

    private void LoadFiles(String inputPath) {
        mLog.add("Loading input files ");
        LoadSource(inputPath);
        LoadLib(inputPath);
        mLog.add("Loading Complete");

        mLog.add("Starting to compare pixel density");
        TreeMap<Float, Float> result = compareDensity();
        mLog.add("Comparison complete");

        mLog.add("Drawing curves");
        drawCurve();
        mLog.add("Drawing complete");

        mLog.add("PixelDensity recognition algorithm finished");
    }

    private void drawCurve() {
        Graph2D graphH = new Graph2D("Pixel Density Horizontal");
        Graph2D graphV = new Graph2D("Pixel Density Vertical");

        for (Letter letter : mLib) {
            addToGraph(graphH, graphV, letter);
        }
    }

    private void addToGraph(Graph2D graphH,  Graph2D graphV, Letter letter) {
        graphH.addCurve(letter.getHorizontalCurve(), letter.getName());
        graphV.addCurve(letter.getVerticalCurve(), letter.getName());
    }

    private TreeMap<Float, Float> compareDensity() {
        TreeMap<Float, Float> resultList = new TreeMap<>();

        for (Letter letter : mLib) {
            mLog.add("Compare " + mSource.getName() + " with " + letter.getName() + " : ");

            CurveComparator curveComparator = new CurveComparator(mSource, letter);
            CurveComparator.CurveAnalyserResult result = curveComparator.getMatchValue();

            mLog.add("\tMatch : " + result.mMatch + "%\t\tError : " + result.mDistError);

            resultList.put(result.mMatch, result.mDistError);
        }

        return resultList;
    }

    private void LoadSource(String inputPath) {
        String fullpath = inputPath + "source.png";

        mLog.add("Loading SOURCE from " + fullpath);

        try {
            mSource = new Letter(fullpath);
        } catch (FileNotFoundException e) {
            mLog.add("Failed to load SOURCE file");
        }
    }

    private void LoadLib(String inputPath) {
        mLog.add("Loading LIB from " + inputPath + "*");

        File folder = new File(inputPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String fullpath = inputPath + listOfFiles[i].getName();
                mLog.add("\tLoading LIB file : " + fullpath);
                try {
                    mLib.add(new Letter(fullpath));
                } catch (FileNotFoundException e) {
                    mLog.add("Failed to load LIB files");
                }
            }
        }
    }
}


