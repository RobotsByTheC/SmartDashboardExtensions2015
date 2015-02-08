package org.usfirst.frc.team2084.smartdashboard.extensions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CompassPlot;

import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractNumberDatasetWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.NumberBindable;
import edu.wpi.first.smartdashboard.properties.ColorProperty;
import edu.wpi.first.smartdashboard.properties.DoubleProperty;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.types.DataType;

/**
 *
 * @author Ben Wolsieffer
 */
@SuppressWarnings("serial")
public class BetterCompass extends AbstractNumberDatasetWidget implements NumberBindable {

    public static final String NAME = "Compass that actually works";

    public BetterCompass() {
        super(0);
    }

    public static final DataType[] TYPES = { DataType.NUMBER };
    public final DoubleProperty circumference = new DoubleProperty(this, "Circumference", 360.0);
    public final ColorProperty ringColor = new ColorProperty(this, "Ring Color", Color.YELLOW);
    // public final MultiProperty needleType = new MultiProperty(this,
    // "Needle Type");
    private JPanel chartPanel;
    private CompassPlot compass;

    @Override
    public void init() {
        // needleType.add("Arrow", 0);
        // needleType.add("Line", 1);
        // needleType.add("Long", 2);
        // needleType.add("Pin", 3);
        // needleType.add("Plum", 4);
        // needleType.add("Pointer", 5);
        // needleType.add("Ship", 6);
        // needleType.add("Wind", 7);
        // needleType.add("Arrow Line", 8);
        // needleType.add("Middle Pin", 9);
        // needleType.setDefault("Arrow Line");

        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout());

            compass = new CompassPlot(getDataset());
            compass.setSeriesNeedle(8);
            // propertyChanged(needleType);
            compass.setSeriesPaint(0, Color.RED);
            compass.setSeriesOutlinePaint(0, Color.RED);

            JFreeChart chart = new JFreeChart(getFieldName(), JFreeChart.DEFAULT_TITLE_FONT, compass, false);
            chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(250, 150));

            propertyChanged(circumference);
            propertyChanged(ringColor);

            add(chartPanel, BorderLayout.CENTER);

            revalidate();
            repaint();
        });
    }

    @Override
    public void propertyChanged(Property property) {
        if (property == circumference) {
            compass.setRevolutionDistance(circumference.getValue());
        } else if (property == ringColor) {
            compass.setRosePaint(ringColor.getValue());
        }// else if (property == needleType) {
         // compass.setSeriesNeedle((int) needleType.getValue());
         // }
    }

    @Override
    public void setBindableValue(double value) {
        setValue(value);
    }
}
