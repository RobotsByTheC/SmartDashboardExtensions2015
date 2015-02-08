/* 
 * Copyright (c) 2015 RobotsByTheC. All rights reserved.
 *
 * Open Source Software - may be modified and shared by FRC teams. The code must
 * be accompanied by the BSD license file in the root directory of the project.
 */
package org.usfirst.frc.team2084.smartdashboard.extensions;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.wpi.first.smartdashboard.gui.elements.bindings.NumberBindable;

@SuppressWarnings("serial")
public class BindableSlider extends JSlider implements NumberBindable, ComponentListener, ChangeListener {

    private double min = 0;
    private double max = 100;
    private double value = 0;
    private double tickSpacing = 10;
    private int pixelWidth = 0;

    public BindableSlider(NumberBindable bindable) {
        calcBounds();
        addComponentListener(this);
        addChangeListener(this);
    }

    @Override
    public void setBindableValue(double value) {
        this.value = value;
        setUnscaledValue(value);
    }

    private void setUnscaledValue(double value) {
        if (value < min) {
            value = min;
        }
        if (value > max) {
            value = max;
        }
        setValue(scaleValue(value));
    }

    private int scaleValue(double unscaledValue) {
        return (int) (((unscaledValue - min) / (max - min)) * pixelWidth);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // Does not work at all!!! major bug!!!
        // if (e.getSource())//TODO implement better delay
        // {
        // bindable.setBindableValue(getUnscaledValue());
        // }
    }

    public void setMin(double min) {
        this.min = min;
        calcBounds();
    }

    public void setMax(double max) {
        this.max = max;
        calcBounds();
    }

    private void calcBounds() {
        pixelWidth = getWidth();
        setMinimum(0);
        setMaximum(pixelWidth);
        setUnscaledValue(value);
        setTickSpacing(tickSpacing);
    }

    public void setTickSpacing(double spacing) {
        tickSpacing = spacing;
        setMajorTickSpacing(scaleValue(spacing));
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
        calcBounds();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        calcBounds();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
