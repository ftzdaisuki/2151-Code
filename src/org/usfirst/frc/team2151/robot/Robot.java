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

//to change the motor power: find gamePad.getRawAxis and edit the multiplying value.
//do this for all getRawAxis values. Be consistent!

public class Robot extends SampleRobot {
    RobotDrive Drive;//The first set of Talons.
    Joystick gamePad; //our gamepad! wooo.
    Relay relayArms; //opening and closing the arms
    Victor armsLift; //lifting said arms
    DigitalInput limitSwitch1;
    DigitalInput limitSwitch2;
    Ultrasonic ultra;
    
    public Robot() {
        Drive = new RobotDrive(0, 1, 2, 3);
        Drive.setExpiration(0.1);
        relayArms = new Relay(0);
        armsLift = new Victor(4);
        gamePad = new Joystick(0); //init code
        limitSwitch1 = new DigitalInput(0);
        limitSwitch2 = new DigitalInput(1);	
        ultra = new Ultrasonic(3,4); //these ports are just used as placeholders. Change to (ULTRASONIC_ECHO_PULSE_OUTPUT, ULTRASONIC_TRIGGER_PULSE_INPUT)
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
        	double leftSide = gamePad.getRawAxis(1) * .60; //60% power
        	double rightSide = gamePad.getRawAxis(2) * .60; //otherwise we move insanely too fast
        	double range = ultra.getRangeInches();

        	
        	while (buttonClose && limitSwitch2.get()) { //entering loops for raising and lowering arms
        		relayArms.set(Relay.Value.kForward);
        		leftSide = gamePad.getRawAxis(1) * .60;
            	rightSide = gamePad.getRawAxis(2) * .60;
            	Drive.arcadeDrive(-leftSide, -rightSide, true);
            	buttonClose = gamePad.getRawButton(6);
            	Timer.delay(0.0001);
        	}
        	
        	while (buttonOpen && limitSwitch1.get()) {
        		relayArms.set(Relay.Value.kReverse); //or raise them
            	leftSide = gamePad.getRawAxis(1) * .60; 
            	rightSide = gamePad.getRawAxis(2) * .60;
            	Drive.arcadeDrive(-leftSide, -rightSide, true); 
            	buttonOpen = gamePad.getRawButton(5); 
            	Timer.delay(0.0001);
        	}
			
        	
        	SmartDashboard.putNumber("Ultrasonic range to nearest tote", range);
        	SmartDashboard.putBoolean("Closing?", buttonClose);
        	SmartDashboard.putBoolean("Opening?", buttonOpen);
        	SmartDashboard.putBoolean("Limit 1", limitSwitch1.get());
        	SmartDashboard.putBoolean("Limit 2", limitSwitch2.get());
        	relayArms.set(Relay.Value.kOff); //we were missing these commands and got very confused when the relays stuck on
        	Drive.arcadeDrive(-leftSide, -rightSide, true); //negated because it's backwards
            Timer.delay(0.0001);		//0.1ms delay for very fast updating (now watch as our CPU usage spikes)
        }
    }
}
   


	