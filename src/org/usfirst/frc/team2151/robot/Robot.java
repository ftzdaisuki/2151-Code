package org.usfirst.frc.team2151.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.Victor;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//to change the motor power: find gamePad.getRawAxis and edit the multiplying value.
//do this for all getRawAxis values. Be consistent!

public class Robot extends SampleRobot {
    RobotDrive Drive; //Talons.
    Joystick gamePad; //our gamepad! wooo.
    Victor relayArms; //grabbing mech
    Victor armsLift; //lifting mech
    DigitalInput limitSwitch1;
    DigitalInput limitSwitch2;
    DigitalInput limitSwitch3;
    DigitalInput limitSwitch4; //4 limit switches (some may be unused)
    Ultrasonic ultra; //ultrasonic rangefinder
    Unit kInches; //why do we need this?
    
    
    public Robot() {
        Drive = new RobotDrive(0, 1, 2, 3);
        Drive.setExpiration(0.1);
        relayArms = new Victor(5);
        armsLift = new Victor(4);
        gamePad = new Joystick(0); //init code
        limitSwitch1 = new DigitalInput(0);
        limitSwitch2 = new DigitalInput(1);
        limitSwitch3 = new DigitalInput(2);
        limitSwitch4 = new DigitalInput(3);        
		ultra = new Ultrasonic(8,9,kInches);
        ultra.setEnabled(true);
        ultra.setAutomaticMode(true);
   }
           
        public void operatorControl() {
        Drive.setSafetyEnabled(true);
        
        while (isOperatorControl() && isEnabled()) {
        	boolean buttonClose = gamePad.getRawButton(6); //for lowering the arms
        	boolean buttonOpen = gamePad.getRawButton(5); //or raising them  
        	double armsLower = gamePad.getRawAxis(2); //for closing the arms
        	double armsRaise = gamePad.getRawAxis(3); //for opening them up again
        	boolean limit1 = limitSwitch1.get();
        	boolean limit2 = limitSwitch2.get();
            double leftSide = gamePad.getRawAxis(1) * .60; //60% power
        	double rightSide = gamePad.getRawAxis(4) * .70; //otherwise we move insanely too fast
        	//double range = ultra.getRangeInches();
        	
        	if (buttonClose && limit2) 		relayArms.set(.5);//arms closing

        	else if (buttonOpen && limit1)  relayArms.set(-.5);//arms opening
        	
        	else 							relayArms.set(0);
        	
        	if (armsRaise < .20 /*&&armsLower < .20*/)    armsLift.set(armsRaise);
        	
        	else if (armsRaise == 0 /*&& armsLower == 0*/) armsLift.set(-.20);
        	
        	else if (armsRaise > 0 /*&& armsLower > 0*/) armsLift.set(-.50);
        	
        	//Above commands tab-spaced for readability (hopefully).
        	Drive.arcadeDrive(-leftSide, -rightSide);
            Timer.delay(0.001);		//1ms delay for very fast updating (now watch our CPU cook an egg!)
            
      }
    }
}