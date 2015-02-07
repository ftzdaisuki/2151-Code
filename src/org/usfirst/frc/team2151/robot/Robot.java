package org.usfirst.frc.team2151.robot;


import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//to change the motor power: find gamePad.getRawAxis and edit the multiplying value.
//do this for all getRawAxis values. Be consistent!


class Class {
	Relay relayArms;
	Victor armsLift;
	Joystick gamePad;
	DigitalInput limit1;
	DigitalInput limit2;
	
	public void TestInit() {
		relayArms = new Relay(0);
		armsLift = new Victor(4);
		gamePad = new Joystick(0);
		limit1 = new DigitalInput(0);
		limit2 = new DigitalInput(1);
	}
	
	public void TestClose() {
		relayArms.set(Relay.Value.kForward);
	}
	public void TestOpen() {
		relayArms.set(Relay.Value.kReverse);
	}
	//One of our mentors insisted on having some code in a class. 
	//We tried this. It failed.
	//That, and it's not worth the hassle considering what we want the class to do.
}



public class Robot extends SampleRobot {
    RobotDrive Drive;//The first set of Talons.
    Joystick gamePad; //our gamepad! wooo.
    Relay relayArms; //opening and closing the arms
    Victor armsLift; //the grabby bit
    DigitalInput limitSwitch1;
    DigitalInput limitSwitch2;
    DigitalInput limitSwitch3;
    DigitalInput limitSwitch4;
    Ultrasonic ultra;
    Unit kInches;
    
    public Robot() {
        Drive = new RobotDrive(0, 1, 2, 3);
        Drive.setExpiration(0.1);
        relayArms = new Relay(0);
        armsLift = new Victor(4);
        gamePad = new Joystick(0); //init code
        limitSwitch1 = new DigitalInput(0);
        limitSwitch2 = new DigitalInput(1);
        limitSwitch3 = new DigitalInput(2);
        limitSwitch4 = new DigitalInput(3);        
		ultra = new Ultrasonic(6,5,kInches); //these ports are just used as placeholders. 
        //Change to (ULTRASONIC_ECHO_PULSE_OUTPUT, ULTRASONIC_TRIGGER_PULSE_INPUT)
        ultra.setEnabled(true);
        ultra.setAutomaticMode(true);
        
        
        
    }
    /* Not 100% sure what to do with this line here:
      ultra = new Ultrasonic(ULTRASONIC_PING, ULTRASONIC_ECHO);
    */
    
    
    
    
      public void operatorControl() {
        Drive.setSafetyEnabled(true);
        
        while (isOperatorControl() && isEnabled()) {
        	boolean buttonClose = gamePad.getRawButton(6); //for lowering the arms
        	boolean buttonOpen = gamePad.getRawButton(5); //or raising them  
        	boolean armsLower = gamePad.getRawButton(8); //for closing the arms
        	boolean armsRaise = gamePad.getRawButton(7); //for opening them up again
        	boolean limit1 = limitSwitch1.get();
        	boolean limit2 = limitSwitch2.get();
            boolean limit3 = limitSwitch3.get();
            boolean limit4 = limitSwitch4.get();
        	double leftSide = gamePad.getRawAxis(1) * .60; //60% power
        	double rightSide = gamePad.getRawAxis(2) * .60; //otherwise we move insanely too fast
        	double range = ultra.getRangeInches();
        	
        	/* toying around with the idea of using the buttons to open and close the arms
        	   while (armsClose && range < 2) {
        		
        	}
        	*/
        	if (buttonClose && limit2) 		relayArms.set(Relay.Value.kForward);

        	else if (buttonOpen && limit1)  relayArms.set(Relay.Value.kReverse); 

        	else 							relayArms.set(Relay.Value.kOff);
        	
        	if (armsRaise && limit3)		armsLift.set(0.5);
        	
        	else if (armsLower && limit4) 	armsLift.set(-0.5);
        	
        	else 							armsLift.set(0);
        	
        	//Above commands tab-spaced for (hopefully) readability.
        	
        	SmartDashboard.putNumber("Distance to Nearest Object", range);
        	SmartDashboard.putBoolean("Opening?", buttonOpen);
        	SmartDashboard.putBoolean("Or closing?", buttonClose);
            SmartDashboard.putBoolean("Raising?", armsRaise);
            SmartDashboard.putBoolean("Or lowering?", armsLower);
        	SmartDashboard.putBoolean("Limit switch 1 open", limitSwitch1.get());
        	SmartDashboard.putBoolean("Limit Switch 2 open",  limitSwitch2.get());  //Spitting stuff out to SmartDashboard.     	
            Drive.arcadeDrive(-leftSide, -rightSide);
            Timer.delay(0.001);		//1ms delay for very fast updating (now watch our CPU cook an egg!)
            
      }
    }
}