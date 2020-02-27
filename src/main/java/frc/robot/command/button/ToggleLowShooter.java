package frc.robot.command.button;

import frc.robot.OI;

/**
 * Toggles Low Shooter system
 */
public class ToggleLowShooter {
    public static double getLowShooterStatus() {
		if (OI.coDriverController.getYButton()) {
			return 1.0;
		} else {
			return 0;
		}
    }
}

