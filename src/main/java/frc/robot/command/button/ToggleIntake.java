package frc.robot.command.button;

import frc.robot.OI;

/**
 * Toggles intake system
 */
public class ToggleIntake {
    public static double getIntakeStatus() {
		if (OI.coDriverController.getYButton()){
			return 1.0;
		} else {
			return 0;
    	}
	}
}

