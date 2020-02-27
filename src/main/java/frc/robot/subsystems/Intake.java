
package frc.robot.subsystems;
import frc.robot.RobotMap;
import frc.robot.command.teleop.*;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	private VictorSP intake;

	public Intake() {
        intake = new VictorSP(RobotMap.intakeMotor);
		
		intake.setInverted(RobotMap.intakeInverse);

	}

	public void setSpeed(double speed) {
		intake.set(speed);
	}

	
	public void initDefaultCommand() {
		setDefaultCommand(new TeleIntake(this));
	}
}