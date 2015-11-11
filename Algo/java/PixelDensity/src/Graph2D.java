import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by nicol on 02/10/2015.
 */
public class Graph2D extends JFrame {

    JFreeChart mChart = null;
    private XYSeriesCollection mDataset = null;

    public Graph2D(String title) {
        super("XY Line Chart Example with JFreechart");

        JPanel chartPanel = createChartPanel(title);
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createChartPanel(String title) {
        String chartTitle = title;
        String xAxisLabel = "Number";
        String yAxisLabel = "Nb Pixel";

        mChart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, mDataset);

        XYPlot plot = mChart.getXYPlot();

        // Line style
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

        // Background
        plot.setBackgroundPaint(Color.DARK_GRAY);

        // Line Color
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        // Column Color
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        return new ChartPanel(mChart);
    }

    public void addCurve(ArrayList<Integer> data, String label) {
        if (mDataset == null) {
            mDataset = new XYSeriesCollection();
        }

        XYSeries serie = new XYSeries(label);

        for (int i = 0; i < data.size(); i++) {
            serie.add(i, data.get(i));
        }

        mDataset.addSeries(serie);
        mChart.getXYPlot().setDataset(mDataset);
    }
}
