/* 
 * Copyright (c) 2015 RobotsByTheC. All rights reserved.
 *
 * Open Source Software - may be modified and shared by FRC teams. The code must
 * be accompanied by the BSD license file in the root directory of the project.
 */
package org.usfirst.frc.team2084.smartdashboard.extensions;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractValueWidget;
import edu.wpi.first.smartdashboard.properties.BooleanProperty;
import edu.wpi.first.smartdashboard.properties.DoubleProperty;
import edu.wpi.first.smartdashboard.properties.MultiProperty;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.smartdashboard.types.DataType;

/**
 *
 * @author Ben Wolsieffer
 */
@SuppressWarnings("serial")
public class Slider extends AbstractValueWidget {

    public static final DataType[] TYPES = { DataType.NUMBER };

    public final DoubleProperty max = new DoubleProperty(this, "Maximum", 100);
    public final DoubleProperty min = new DoubleProperty(this, "Minimum", 0);
    public final DoubleProperty tickInterval = new DoubleProperty(this, "Tick Interval", 10);
    public final BooleanProperty showTicks = new BooleanProperty(this, "Show Ticks", false);
    public final BooleanProperty showLabel = new BooleanProperty(this, "Show Label", true);
    public final MultiProperty orientation = new MultiProperty(this, "Orientation");
    public final BooleanProperty editable = new BooleanProperty(this, "Editable", true);

    private BindableSlider slider;
    private JLabel label;

    public Slider() {
        orientation.add("Horizontal", NumberSlider.HORIZONTAL);
        orientation.add("Vertical", NumberSlider.VERTICAL);
        orientation.setDefault("Horizontal");
    }

    @Override
    public void init() {
        slider = new BindableSlider(new BindableTableEntry(Robot.getTable(), getFieldName()));

        setNumberBinding(slider);

        update(max, max.getValue());
        update(min, min.getValue());
        update(tickInterval, tickInterval.getValue());
        update(showTicks, showTicks.getValue());
        update(orientation, orientation.getValue());
        update(editable, editable.getValue());

        setLayout(new BorderLayout());
        add(label = new JLabel(getFieldName()), BorderLayout.PAGE_START);
        update(showLabel, showLabel.getValue());

        add(slider, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    @Override
    public void propertyChanged(Property property) {
        if (property == min) {
            slider.setMin(min.getValue());
        } else if (property == max) {
            slider.setMax(max.getValue());
        } else if (property == tickInterval) {
            slider.setTickSpacing(tickInterval.getValue());
        } else if (property == showTicks) {
            slider.setPaintTicks(showTicks.getValue());
            slider.setTickSpacing(tickInterval.getValue());
        } else if (property == showLabel) {
            label.setVisible(showLabel.getValue());
        } else if (property == orientation) {
            slider.setOrientation((int) orientation.getValue());
        } else if (property == editable) {
            slider.setEnabled(editable.getValue());
        }
    }
}
