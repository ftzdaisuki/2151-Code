package org.usfirst.frc.team2151.robot;


import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it 
 * contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    RobotDrive Tier1;//The first set of Talons.
    RobotDrive Tier2;//The second set of Talons.
    Joystick gamePad; //our gamepad! wooo.
    Relay relayArms; //our arms lifting mech
    
    public Robot() {
        Tier1 = new RobotDrive(0, 2);
        Tier1.setExpiration(0.1);
        Tier2 = new RobotDrive(1, 3);
        Tier2.setExpiration(0.1);
        relayArms = new Relay(0);
        gamePad = new Joystick(0); //init code
        
        
    }

    
    /**
     * Runs the motors with tank steering.
     */
    public void operatorControl() {
        Tier1.setSafetyEnabled(true);
        Tier2.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	boolean wat = gamePad.getRawButton(5); //for lowering the arms
        	boolean buttonRaise = gamePad.getRawButton(6); //or raising them    	
        	double leftSide = gamePad.getRawAxis(1) * .75; //divide by two because
        	double rightSide = gamePad.getRawAxis(3) * .75; //way too powerful otherwise
            	
        	//if (leftSide < 0)
            //	leftSide = leftSide * leftSide * -1; //Exponential increases, we need If/Then logic here
           // else
           // 	leftSide = leftSide *leftSide;
           // if (rightSide < 0)
            //	rightSide = rightSide * rightSide * -1; //Multiply by -1 to make the value negative 
            //else            //if the input was negative, since squaring
            //	rightSide = rightSide * rightSide;      //a negative will always make a positive and
        	
        	
        	
        	while (wat) { //entering loops for raising and lowering arms
        		relayArms.set(Relay.Value.kOn); //lower the arms
        		wat = gamePad.getRawButton(5);
        		leftSide = gamePad.getRawAxis(1) * .75;
            	rightSide = gamePad.getRawAxis(3) * .75;
            	Tier1.tankDrive(-leftSide, -rightSide);
            	Tier2.tankDrive(-leftSide, -rightSide);
            	Timer.delay(0.001);
        	}
        	
        	while (buttonRaise) {
        		relayArms.set(Relay.Value.kReverse); //or raise them
        		buttonRaise = gamePad.getRawButton(6);    	
            	leftSide = gamePad.getRawAxis(1) * .75; 
            	rightSide = gamePad.getRawAxis(3) * .75;
            	Tier1.tankDrive(-leftSide, -rightSide); 
            	Tier2.tankDrive(-leftSide, -rightSide);
            	Timer.delay(0.001);
        	}
        	
        	relayArms.set(Relay.Value.kOff);
        	Tier1.tankDrive(-leftSide, -rightSide); //negated because it's backwards
        	Tier2.tankDrive(-leftSide, -rightSide);
            Timer.delay(0.001);		//add a bit of delay to ensure nothing breaks
        }
    }
}
   

