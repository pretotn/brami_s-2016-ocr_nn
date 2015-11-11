package generator;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by home on 28/10/2015.
 */
public class TrainingFileGenerator {

    private int mPictureSize = -1;
    private String mCharLib;

    public ArrayList<Pair<Character, BufferedImage>> mFileList;

    public TrainingFileGenerator(String charLib) {
        mFileList = new ArrayList<>();
        mCharLib = charLib;
    }

    public void addFile(Character letter, String path) {
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("ERROR : " + path + " does not exists");
            System.exit(-1);
        }

        try {
            BufferedImage imageFile = ImageIO.read(file);

            if (mPictureSize == -1) {
                mPictureSize = imageFile.getHeight() * imageFile.getWidth();
            } else if (mPictureSize != imageFile.getHeight() * imageFile.getWidth()) {
                System.out.println("Error on images size : " + path);
                return;
            }

            Pair<Character, BufferedImage> pair = new Pair<>(letter, imageFile);

            mFileList.add(pair);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateFile() {
        String path = null;
        try {
            path = generate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (path != null) {
            System.out.println("Success : file generated and stored at : " + path);
        } else {
            System.out.println("Error : file not generated");
        }
        return path;
    }

    private String generate() throws IOException {

        // Create Training File
        File datasetFile = new File("TrainingDataSet.txt");
        FileWriter fw = new FileWriter(datasetFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        // Write header
        bw.write(mFileList.size() + " " + mPictureSize + " " + mCharLib.length());

        // Print dataset
        for (Pair<Character, BufferedImage> item : mFileList) {

            // New Line in file
            bw.newLine();

            // Print Input pixels
            for (int y = 0; y < item.getValue().getHeight(); y++) {
                for (int x = 0; x < item.getValue().getWidth(); x++) {
                    bw.write((item.getValue().getRGB(x, y) == -1 ? "0" : "1"));
                    bw.write(x + 1 < item.getValue().getWidth() ? " " : "");
                }
                bw.write(y + 1 < item.getValue().getHeight() ? " " : "");
            }

            // New Line in file
            bw.newLine();

            // Print expected result
            for (int i = 0; i < mCharLib.length(); i++) {
                bw.write(i == mCharLib.indexOf(item.getKey()) ? "1" : "0");
                bw.write(i + 1 < mCharLib.length() ? " " : "");
            }

        }

        bw.close();

        return datasetFile.getPath();
    }
}
