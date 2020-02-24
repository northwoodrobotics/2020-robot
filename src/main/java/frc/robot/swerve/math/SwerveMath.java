package frc.robot.swerve.math;

import java.util.Arrays;
import java.util.List;

/**
 * This is the main class for the swerveDrive library.
 * The library handles the calculations required to drive a robot using SwerveDrive wheels. These wheels have two motors:
 * A drive motor that moves the robot and a turn motor that changes the wheel assembly's direction.
 *
 * MOTOR LAYOUT
 *
 * 					Front
 * 		Wheel 2 -------------- Wheel 1
 * 			|					|
 * 			|					|
 * 			|					|
 *	Left 	|					|   Right
 * 			|					|
 * 			|					|
 * 			|					|
 * 		Wheel 3 -------------- Wheel 4
 * 					Back
 *
 * The library supports two modes: Robot centric and Field centric. In Robot centric mode the robot turns relative to its
 * current position: 45 degrees to the right will turn the robot 45 degrees to the right (for example, if it is pointing
 * north before the turn, it will point north-east after the turn. In Field centric mode the robot turns to face the given
 * number of degrees relative to the firld's orientation: 0 means straight ahead down the field, 90 means to the right, etc.
 */
public class SwerveMath {
    // Robot dimensions. Units are of no importance. Required
    private final double side;

    private final double length;
    private final double width;

    // The diagonal of the robot dimensions. Internal
    private final double diagonal;


    // The scale factor to control robot maximum speed. Optional.
    private final double SCALE_SPEED = 1.00;

    // The "Centric" mode for the robot
    	private CentricMode centricMode = CentricMode.ROBOT;

    public void setModeField() {
		centricMode= CentricMode.FIELD;
	}
    /**
     * Constructor
     * @param side the robot side length in inches
     * 
    */

    public SwerveMath(double side) {
        assert (side > 0) : "Side has to be larger than 0";
        this.side = side;
        this.length = (2 * this.side);
        this.width = (Math.sqrt(3) * this.side);
        diagonal = (Math.sqrt(Math.pow(this.length,2) + Math.pow(this.width,2)));
    }


    public CentricMode getCentricMode() {
        return centricMode;
    }


    public void setCentricMode(CentricMode centricMode) {
        this.centricMode = centricMode;
    }


    /**
     * move
     * Moves the robot based on 3 inputs - fwd (forward), str(strafe), and rcw(rotation clockwise)
     * Inputs are between -1 and 1, with 1 being full power, -1 being full reverse, and 0 being neutral.
     * The method uses gyro for field centric driving, if it is enabled.
     * @param fwd the forward power value range -1.0(back) - 1.0(fwd)
     * @param str the strafe power value range -1.0(left) - 1.0(right)
     * @param rcw the rotation power value range -1.0(ccw) - 1.0(cw)
     * @param gyroValue the value of the gyro input to be used by the calculation. Optional. Only used when the robot is in field-centric mode. Values are 0-360
     * @return List of wheel movement directives. The list indices correspond to the wheel numbering scheme as above, zero-based.
     */
    public List<SwerveDirective> move(double fwd, double str, double rcw, Double gyroValue) {

        if ((gyroValue == null) && centricMode.equals(CentricMode.FIELD)) {
            throw new IllegalStateException("Cannot use field centric mode without a Gyro value");
        }
        
        //Adjust for Gyro (if wanted)
        if (isFieldCentric()){
            //Convert the gyro angle (in degrees) to radians.
            double gyro = (gyroValue * Math.PI) / 180;

            double temp = fwd * Math.cos(gyro) + str * Math.sin(gyro);
            str = -fwd * Math.sin(gyro) + str * Math.cos(gyro);
            fwd = temp;
        }

        //These 8 variables are used in the swerve drive calculations.

        //Default
        double a = str - rcw*(length / diagonal);
        double b = str + rcw*(length / diagonal);
        double c = fwd - rcw*(width / diagonal);
        double d = fwd + rcw*(width / diagonal);

        //These are the equations for the wheel speed, for motors 1-5.
        
        double wsLF =  Math.sqrt( Math.pow( ((b*0.75) + (a*0.25)),2) + Math.pow(((Math.sqrt(3)/2) *d)+(1-(Math.sqrt(3)/2)*c),2));
        double wsRF =  Math.sqrt( Math.pow( ((b*0.75) + (a*0.25)),2) + Math.pow(((Math.sqrt(3)/2) *c)+(1-(Math.sqrt(3)/2)*d),2));
        double wsRB =  Math.sqrt( Math.pow( ((a*0.75) + (b*0.25)),2) + Math.pow(((Math.sqrt(3)/2) *c)+(1-(Math.sqrt(3)/2)*d),2));
        double wsLB =  Math.sqrt( Math.pow( ((a*0.75) + (b*0.25)),2) + Math.pow(((Math.sqrt(3)/2) *d)+(1-(Math.sqrt(3)/2)*c),2));
        double wsB =  Math.sqrt( Math.pow(a,2) + Math.pow(d/2 + c/2,2));

        //These are the equations for the wheel angle, for motors 1-5.
        double waLF =  Math.atan2((((b*0.75) + (a*0.25))),(((Math.sqrt(3)/2) *d)+(1-(Math.sqrt(3)/2)*c)))*180/Math.PI;
        double waRF =  Math.atan2((((b*0.75) + (a*0.25))),(((Math.sqrt(3)/2) *c)+(1-(Math.sqrt(3)/2)*d)))*180/Math.PI;
        double waRB =  Math.atan2((((a*0.75) + (b*0.25))),(((Math.sqrt(3)/2) *c)+(1-(Math.sqrt(3)/2)*d)))*180/Math.PI;
        double waLB =  Math.atan2((((a*0.75) + (b*0.25))),(((Math.sqrt(3)/2) *d)+(1-(Math.sqrt(3)/2)*c)))*180/Math.PI;
        double waB =  Math.atan2((a),((d/2) + (c/2)))*180/Math.PI;

        //This is to normalize the speed (if the largest speed is greater than 1, change accordingly).
        double max = wsLF;
        if(wsRF>max) max = wsRF;
        if(wsRB>max) max = wsRB;
        if(wsLB>max) max = wsLB;
        if(wsB>max) max = wsB;
        if(max>1){
            wsLF/=max;
            wsRF/=max;
            wsRB/=max;
            wsLB/=max;
            wsB/=max;
        }

        //Wheel angle was in the range of -180 to 180. Now its -.5 to .5
        waLF/=360;
        waRF/=360;
        waRB/=360;
        waLB/=360;
        waB/=360;


        //Used to scale the movement speeds for testing (so you don't crash into walls)
        wsLF*=SCALE_SPEED;
        wsRF*=SCALE_SPEED;
        wsRB*=SCALE_SPEED;
        wsLB*=SCALE_SPEED;
        wsB*=SCALE_SPEED;

        SwerveDirective directiveLF = new SwerveDirective(waLF, wsLF);
        SwerveDirective directiveRF = new SwerveDirective(waRF, wsRF);
        SwerveDirective directiveRB = new SwerveDirective(waRB, wsRB);
        SwerveDirective directiveLB = new SwerveDirective(waLB, wsLB);
        SwerveDirective directiveB = new SwerveDirective(waB, wsB);

        return Arrays.asList(directiveLF, directiveRF, directiveRB, directiveLB, directiveB);
    }

    private boolean isFieldCentric() {
        return centricMode.equals(CentricMode.FIELD);
    }

    

}
