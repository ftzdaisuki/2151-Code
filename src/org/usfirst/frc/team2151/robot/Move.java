package org.usfirst.frc.team2151.robot;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Move {

	 	static RobotDrive Drive;//The first set of Talons.
	    static Joystick gamePad; //our gamepad! wooo.
	    Relay relayArms; //opening and closing the arms
	    Victor armsLift; //the grabby bit
	    DigitalInput limitSwitch1;
	    DigitalInput limitSwitch2;
	    Ultrasonic ultra;
	
	public static void botDrive() {
		
		boolean buttonLower = gamePad.getRawButton(6); //for lowering the arms
    	boolean buttonRaise = gamePad.getRawButton(5); //or raising them    	
    	double leftSide = gamePad.getRawAxis(1) * .60; //50% power
    	double rightSide = gamePad.getRawAxis(2) * .60;
    	
    	Drive.arcadeDrive(-leftSide,-rightSide);
		
	}
	
}
