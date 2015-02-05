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
import org.usfirst.frc.team2151.robot.Move;

//to change the motor power: find gamePad.getRawAxis and edit the multiplying value.
//do this for all getRawAxis values. Be consistent!

public class Robot extends SampleRobot {
    RobotDrive Drive;//The first set of Talons.
    Joystick gamePad; //our gamepad! wooo.
    Relay relayArms; //opening and closing the arms
    Victor armsLift; //the grabby bit
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
        	boolean buttonLower = gamePad.getRawButton(6); //for lowering the arms
        	boolean buttonRaise = gamePad.getRawButton(5); //or raising them    	
        	double leftSide = gamePad.getRawAxis(1) * .60; //50% power
        	double rightSide = gamePad.getRawAxis(2) * .60; //otherwise we move insanely too fast
        	double range = ultra.getRangeInches();

        	
        	while (buttonLower && limitSwitch2.get()) { //entering loops for raising and lowering arms
        		relayArms.set(Relay.Value.kForward);
        		Move.botDrive();
            	Timer.delay(0.001);
        	}
        	
        	while (buttonRaise && limitSwitch1.get()) {
        		relayArms.set(Relay.Value.kReverse); //or raise them
            	Move.botDrive();
            	Timer.delay(0.001);
        	}
			
        	
        	Move.botDrive();
        	relayArms.set(Relay.Value.kOff); //we were missing these commands and got very confused when the relays stuck on
            Timer.delay(0.001);		//1ms delay for very fast updating (now watch as we run out of memory)
        }
    }
}
   


	