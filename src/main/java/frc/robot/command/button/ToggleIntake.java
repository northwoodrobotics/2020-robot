package frc.robot.command.button;

import frc.robot.OI;

/**
 * Toggles intake system
 */
public class ToggleIntake {
    public static double getIntakeStatus() {
		if (OI.driveController.getYButton() && OI.driveController.getBButton()) {
			return 0;
		} else if (OI.driveController.getYButton()){
			return 1.0;
		} else if (OI.driveController.getBButton()){
			return -1.0;
		}
		return 0;
    }
}

