
package frc.robot.subsystems;
import frc.robot.RobotMap;
import frc.robot.command.teleop.*;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	private VictorSP m1;

	public Intake() {
        m1 = new VictorSP(RobotMap.intakeMotor);
		
		m1.setInverted(RobotMap.intakeInverse);

	}

	public void setSpeed(double speed) {
		m1.set(speed);
	}

	
	public void initDefaultCommand() {
		setDefaultCommand(new TeleIntake(this));
	}
}