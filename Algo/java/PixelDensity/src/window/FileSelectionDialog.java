package window;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nicol on 03/10/2015.
 */
public class FileSelectionDialog extends JFrame {

    public FileSelectionDialog() {
        setTitle("Choose your files");

        setSize(1200, 200);
        setResizable(false);
        setVisible(true);

        Container contentPane = getContentPane();

        GridLayout gridLayout = new GridLayout(2,2);
        contentPane.setLayout(gridLayout);

        // Form
        JLabel labelFile = new JLabel("Choose file");
        JLabel labelLib = new JLabel("Choose lib directory");
        JButton buttonFileChooser = new JButton("Select file");
        JButton buttonLibChooser = new JButton("Select Directory");

        JFileChooser fileChooser = new JFileChooser();
        JFileChooser libChooser = new JFileChooser();

        int returnVal = fileChooser.showOpenDialog(null);

        contentPane.add(labelFile);
        contentPane.add(buttonFileChooser);
        contentPane.add(labelLib);
        contentPane.add(buttonLibChooser);



    }


}
