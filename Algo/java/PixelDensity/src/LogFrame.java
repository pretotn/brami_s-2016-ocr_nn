import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.awt.geom.Arc2D;
import java.io.File;
import java.util.*;


/**
 * Created by nicol on 03/10/2015.
 */
public class LogFrame extends JFrame {

    TextArea mLogArea;

    Calendar mCalendar;
    SimpleDateFormat mDateFormat;

    public LogFrame() {
        super();
        setSize(800, 600);
        setVisible(true);

        Container contentPanel = getContentPane();

        mLogArea = new TextArea();
        mLogArea.setEditable(false);

        contentPanel.add(mLogArea);

        mCalendar = Calendar.getInstance();
        mDateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    private String getTime() {
        return mDateFormat.format(mCalendar.getTime());
    }

    public void add(String log) {

        if (!mLogArea.getText().equals("")) {
            mLogArea.append("\n");
        }
        mLogArea.append("[" + getTime() + "] " + log);
    }
}
