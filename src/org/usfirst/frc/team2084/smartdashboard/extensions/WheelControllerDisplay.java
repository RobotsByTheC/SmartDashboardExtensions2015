/* 
 * Copyright (c) 2015 RobotsByTheC. All rights reserved.
 *
 * Open Source Software - may be modified and shared by FRC teams. The code must
 * be accompanied by the BSD license file in the root directory of the project.
 */
package org.usfirst.frc.team2084.smartdashboard.extensions;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import edu.wpi.first.smartdashboard.gui.elements.SimpleDial;
import edu.wpi.first.smartdashboard.gui.elements.TextBox;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.types.DataType;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * @author Ben Wolsieffer
 */
@SuppressWarnings("serial")
public class WheelControllerDisplay extends AbstractTableWidget {

    public static final String VALUE_FIELD = "Value";

    public static final DataType[] TYPES = { WheelControllerType.get() };

    private final BorderLayout layout = new BorderLayout();

    private final SimpleDial value = new SimpleDial();

    private final JPanel fieldPanel = new JPanel();

    @Override
    public void propertyChanged(Property property) {
    }

    @Override
    public void init() {
        setNumberBinding(VALUE_FIELD, value::setValue);

        SwingUtilities.invokeLater(() -> {
            setLayout(layout);

            value.setType(DataType.NUMBER);
            value.setFieldName(VALUE_FIELD);
            value.init();
            value.min.setValue(-1.0);
            value.max.setValue(1.0);
            value.tickInterval.setValue(0.1);

            fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.PAGE_AXIS));

            add(new JLabel(getFieldName(), SwingConstants.CENTER), BorderLayout.NORTH);
            add(value, BorderLayout.CENTER);
            add(fieldPanel, BorderLayout.SOUTH);
            revalidate();
            repaint();
        });
    }

    private TextBox createUneditableNumberBox(String field) {
        TextBox tb = new TextBox();
        tb.setType(DataType.NUMBER);
        tb.setFieldName(field);
        tb.init();
        tb.editable.setValue(false);
        return tb;
    }

    @Override
    public void doubleChanged(ITable source, String key, double value, boolean isNew) {
        if (isNew && !key.equals(VALUE_FIELD)) {
            TextBox unb = createUneditableNumberBox(key);
            setNumberBinding(key, unb::setValue);
            SwingUtilities.invokeLater(() -> {
                fieldPanel.add(unb);
                revalidate();
                repaint();
            });
        }

        super.doubleChanged(source, key, value, isNew);
    }
}
