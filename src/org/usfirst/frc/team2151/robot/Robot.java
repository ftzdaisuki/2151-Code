package org.usfirst.frc.team2151.robot;


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


class ttClass {
	
	public static void TestClose(Victor relayArms) {
		relayArms.set(1);
	}
	public static void TestOpen(Victor relayArms) {
		relayArms.set(-1);
	}
	public void TestLower(Victor armsLift) {
		armsLift.set(0.5);
	}
	public void TestRaise(Victor armsLift){
		armsLift.set(0.5);
	}
}



public class Robot extends SampleRobot {
    RobotDrive Drive;//Talons.
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
        	boolean armsLower = gamePad.getRawButton(8); //for closing the arms
        	boolean armsRaise = gamePad.getRawButton(7); //for opening them up again
        	boolean limit1 = limitSwitch1.get();
        	boolean limit2 = limitSwitch2.get();
            boolean limit3 = limitSwitch3.get();
            boolean limit4 = limitSwitch4.get();
        	double leftSide = gamePad.getRawAxis(1) * .60; //60% power
        	double rightSide = gamePad.getRawAxis(2) * .40; //otherwise we move insanely too fast
        	double range = ultra.getRangeInches();
        	
        	if (buttonClose && limit2) 		relayArms.set(.25);

        	else if (buttonOpen && limit1 && range < 28)  relayArms.set(-.25);
        	
        	else 							relayArms.set(0);
        	
        	if (armsRaise && limit3)		armsLift.set(-.8);
        	
        	else if (armsLower && limit4) 	armsLift.set(.1);
        	
        	else 							armsLift.set(0);
        	
        	//Above commands tab-spaced for readability (hopefully).
        	
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