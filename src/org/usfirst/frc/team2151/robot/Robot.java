package org.usfirst.frc.team2151.robot;


import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;
//to change the motor power: find gamePad.getRawAxis and edit the multiplying value.
//do this for all getRawAxis values. Be consistent!
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
    RobotDrive Drive;//The first set of Talons.
    Joystick gamePad; //our gamepad! wooo.
    Relay relayArms; //our arms lifting mech
    Relay relayGrab; //the grabby bit
    DigitalInput limitSwitch1;
    DigitalInput limitSwitch2;
    
    public Robot() {
        Drive = new RobotDrive(0, 1, 2, 3);
        Drive.setExpiration(0.1);
        relayArms = new Relay(0);
        gamePad = new Joystick(0); //init code
        limitSwitch1 = new DigitalInput(0);
        limitSwitch2 = new DigitalInput(1);
        
        
    }

    
      public void operatorControl() {
        Drive.setSafetyEnabled(true);
        
        while (isOperatorControl() && isEnabled()) {
        	boolean buttonLower = gamePad.getRawButton(6); //for lowering the arms
        	boolean buttonRaise = gamePad.getRawButton(5); //or raising them    	
        	double leftSide = gamePad.getRawAxis(1) * .5; //50% power
        	double rightSide = gamePad.getRawAxis(2) * .5; //otherwise we move insanely too fast
        	
        	while (buttonLower && limitSwitch2.get()) { //entering loops for raising and lowering arms
        		relayArms.set(Relay.Value.kForward);
        		leftSide = gamePad.getRawAxis(1) * .50;
            	rightSide = gamePad.getRawAxis(2) * .50;
            	Drive.arcadeDrive(-leftSide, -rightSide, true);
            	buttonLower = gamePad.getRawButton(6);
            	Timer.delay(0.001);
        	}
        	
        	while (buttonRaise && limitSwitch1.get()) {
        		relayArms.set(Relay.Value.kReverse); //or raise them
            	leftSide = gamePad.getRawAxis(1) * .50; 
            	rightSide = gamePad.getRawAxis(2) * .50;
            	Drive.arcadeDrive(-leftSide, -rightSide, true); 
            	buttonRaise = gamePad.getRawButton(5); 
            	Timer.delay(0.001);
        	}
			
        	
        	
        	relayArms.set(Relay.Value.kOff); //we were missing these commands and got very confused when the relays stuck on
        	Drive.arcadeDrive(-leftSide, -rightSide, true); //negated because it's backwards
            Timer.delay(0.001);		//1ms delay for very fast updating (now watch as we run out of memory)
        }
    }
}
   


	