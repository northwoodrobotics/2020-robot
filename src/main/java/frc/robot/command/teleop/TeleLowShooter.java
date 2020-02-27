package frc.robot.command.teleop;

import frc.robot.command.button.ToggleLowShooter;
import frc.robot.subsystems.LowShooter;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleLowShooter extends Command {

    private LowShooter lowShooter;

	public TeleLowShooter(LowShooter lowShooter) {
    	this.lowShooter = lowShooter;
    	requires(lowShooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        lowShooter.setSpeed(ToggleLowShooter.getLowShooterStatus());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}