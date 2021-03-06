package frc.robot.command.teleop;

import frc.robot.command.button.ToggleIntake;
import frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleIntake extends Command {

    private Intake intake;

	public TeleIntake(Intake intake) {
    	this.intake = intake;
    	requires(intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        intake.setSpeed(ToggleIntake.getIntakeStatus());
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