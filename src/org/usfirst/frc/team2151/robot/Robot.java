package org.usfirst.frc.team2151.robot;


import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;


public class Robot extends SampleRobot {
    RobotDrive Tier1;//The first set of Talons.
    RobotDrive Tier2;//The second set of Talons.
    Joystick gamePad; //our gamepad! wooo.
    Relay relayArms; //our arms lifting mech
    Relay relayGrab; //the grabby bit
    
    public Robot() {
        Tier1 = new RobotDrive(0, 2);
        Tier1.setExpiration(0.1);
        Tier2 = new RobotDrive(1, 3);
        Tier2.setExpiration(0.1);
        relayArms = new Relay(0);
        relayGrab = new Relay(1);
        gamePad = new Joystick(0); //init code
        
        
    }

    
    /**
     * Runs the motors with tank steering.
     */
    public void operatorControl() {
        Tier1.setSafetyEnabled(true);
        Tier2.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	boolean buttonLower = gamePad.getRawButton(5); //for lowering the arms
        	boolean buttonRaise = gamePad.getRawButton(6); //or raising them    	
        	boolean buttonRelease = gamePad.getRawButton(7); //let the boxes go
        	boolean buttonGrab = gamePad.getRawButton(8); //or grab them (backwards because OCD)
        	double leftSide = gamePad.getRawAxis(1) * .75; //75% power
        	double rightSide = gamePad.getRawAxis(3) * .75; //otherwise we move insanely too fast
            	
        	//if (leftSide < 0)
            //	leftSide = leftSide * leftSide * -1; //Exponential increases, we need If/Then logic here
           // else
           // 	leftSide = leftSide *leftSide;
           // if (rightSide < 0)
            //	rightSide = rightSide * rightSide * -1; //Multiply by -1 to make the value negative 
            //else            //if the input was negative, since squaring
            //	rightSide = rightSide * rightSide;      //a negative will always make a positive and
        	
        	//Above is a motor curve logic bit. Uncomment if you wish to test it.
        	
        	while (buttonLower) { //entering loops for raising and lowering arms
        		relayArms.set(Relay.Value.kForward); //lower the arms
        		leftSide = gamePad.getRawAxis(1) * .75;
            	rightSide = gamePad.getRawAxis(3) * .75;
            	Tier1.tankDrive(-leftSide, -rightSide);
            	Tier2.tankDrive(-leftSide, -rightSide);
            	buttonLower = gamePad.getRawButton(5);
            	Timer.delay(0.001);
        	}
        	
        	while (buttonRaise) {
        		relayArms.set(Relay.Value.kReverse); //or raise them
            	leftSide = gamePad.getRawAxis(1) * .75; 
            	rightSide = gamePad.getRawAxis(3) * .75;
            	Tier1.tankDrive(-leftSide, -rightSide); 
            	Tier2.tankDrive(-leftSide, -rightSide);
            	buttonRaise = gamePad.getRawButton(6); 
            	Timer.delay(0.001);
        	}
        	while (buttonGrab) {
        		relayGrab.set(Relay.Value.kForward); //grab a tote/recycle bin
            	leftSide = gamePad.getRawAxis(1) * .75; 
            	rightSide = gamePad.getRawAxis(3) * .75;
            	Tier1.tankDrive(-leftSide, -rightSide); 
            	Tier2.tankDrive(-leftSide, -rightSide);
            	buttonGrab = gamePad.getRawButton(7); 
            	Timer.delay(0.001);
        	
        	}
        	while(buttonRelease) {
        	
        		relayGrab.set(Relay.Value.kReverse); //or let it go
        		leftSide = gamePad.getRawAxis(1) * .75; 
        		rightSide = gamePad.getRawAxis(3) * .75;
        		Tier1.tankDrive(-leftSide, -rightSide); 
        		Tier2.tankDrive(-leftSide, -rightSide);
        		buttonRelease = gamePad.getRawButton(8); 
        		Timer.delay(0.001);
        	}
        	relayGrab.set(Relay.Value.kOff); //ensure our relays stay off if no button is being pressed
        	relayArms.set(Relay.Value.kOff); //we were missing these commands and got very confused when the relays stuck on
        	Tier1.tankDrive(-leftSide, -rightSide); //negated because it's backwards
        	Tier2.tankDrive(-leftSide, -rightSide);
            Timer.delay(0.001);		//1ms delay for very fast updating (now watch as we run out of memory)
        }
    }
}
   

