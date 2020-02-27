/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// Motor Map
	// I = inverse, S = steer

	//Color sensor port
	public static I2C.Port colorSensorPort = I2C.Port.kOnboard;

	//Front right motor
	public static int Frontright = 1;
	public static int FrontrightS = 2;
	public static boolean FrontrightI = false;
	public static boolean FrontrightSI = false;
	
	//Back right motor
	public static int Backright = 3;
	public static int BackrightS = 4;
	public static boolean BackrightI = false;
	public static boolean BackrightSI = false;
	
	//Back motor
	public static int Back = 5;
	public static int BackS = 6;
	public static boolean BackI = false;
	public static boolean BackSI = true;

	//Back left motor
	public static int Backleft = 7;
	public static int BackleftS = 8;
	public static boolean  BackleftI = true;
	public static boolean  BackleftSI = false;

	//Front left motor
	public static int Frontleft = 9;
	public static int FrontleftS = 10;
	public static boolean FrontleftI = true;
	public static boolean FrontleftSI = false;

	//Intake motor
	public static int intakeMotor = 0;
	public static boolean intakeInverse = false;

	//Low shoot motor
	public static int lowShootMotor = 1;
	public static boolean lowShootInverse = false;
	



	

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

}
