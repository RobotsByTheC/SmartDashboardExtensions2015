/* 
 * Copyright (c) 2015 RobotsByTheC. All rights reserved.
 *
 * Open Source Software - may be modified and shared by FRC teams. The code must
 * be accompanied by the BSD license file in the root directory of the project.
 */
package org.usfirst.frc.team2084.smartdashboard.extensions;

import edu.wpi.first.smartdashboard.types.NamedDataType;

/**
 * @author Ben Wolsieffer
 */
public class WheelControllerType extends NamedDataType {

    public static final String LABEL = "Wheel Controller";

    /**
     * @param name
     * @param parents
     */
    protected WheelControllerType() {
        super(LABEL, WheelControllerDisplay.class);
    }

    public static NamedDataType get() {
        if (NamedDataType.get(LABEL) != null) {
            return NamedDataType.get(LABEL);
        } else {
            return new WheelControllerType();
        }
    }
}
