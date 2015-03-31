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
import edu.wpi.first.wpilibj.PowerDistributionPanel;

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
    PowerDistributionPanel power;
    
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
        power = new PowerDistributionPanel();
   }
    	public void autonomous() {
    		Drive.setSafetyEnabled(false);
    		/*	Drive.tankDrive(.6, .6);
    			Timer.delay(3.0);
    			Drive.tankDrive(0,0);
    			Drive.tankDrive(-.25, .25);
    			*/
    		double t = 1;
    		     ///*
    			 Drive.tankDrive(.6, .6);//1, move forward to tote
    			 Timer.delay(.2);
    			 Drive.tankDrive(0, 0);
    			 Timer.delay(t);
    			 relayArms.set(.75);//2, close arm
    			 Timer.delay(0.1);
    			 relayArms.set(0);
    			 Timer.delay(t);
<<<<<<< HEAD
    			 armsLift.set(-.8);//2, lift arm
<<<<<<< HEAD
    			 Timer.delay(1);
    			 armsLift.set(-.15); // keeps the arms in the air
    			 Timer.delay(t);
    			 Drive.tankDrive(.55, .55);//3, move forward with tote
    			 Timer.delay(5);
    			 Drive.tankDrive(0, 0);
    			 Timer.delay(t);
    			 armsLift.set(0); //4, lowering arms post-auto
    			 Timer.delay(2);
    			 Timer.delay(t);
    			 relayArms.set(-.75);//5, re-opening the arms
    			 Timer.delay(0.1);
    			 relayArms.set(0);
=======
<<<<<<< HEAD
    			 Timer.delay(1);
    			 armsLift.set(-.15); //3, keeps the arms in the air
=======
    			 armsLift.set(-.8);//3, lift arm
    			 Timer.delay(.75);
    			 armsLift.set(-.15); //keeps the arms in the air
>>>>>>> origin/master
    			 Timer.delay(t);
    			 Drive.tankDrive(.6, .6);//4, move forward with tote
    			 Timer.delay(3.8);
    			 Drive.tankDrive(0, 0);
<<<<<<< HEAD
    			 Timer.delay(t);
    			 armsLift.set(0); //5, lowering arms post-auto
    			 Timer.delay(2);
    			 Timer.delay(t);
    			 relayArms.set(-.75);//6, re-opening the arms
    			 Timer.delay(0.1);
    			 relayArms.set(0);
=======
>>>>>>> origin/master
=======
    			 Timer.delay(.25);
    			 armsLift.set(0);
>>>>>>> parent of 089cd3a... More script tweaks.
>>>>>>> origin/master
    			 Timer.delay(t);
    			 /*Drive.tankDrive(-.25, .25);//4, turn 90*
    			 Timer.delay(.5);
    			 Drive.tankDrive(0, 0);
    			 Timer.delay(t);
    			 Drive.tankDrive(.6, .6);//5, to next tote
    			 Timer.delay(1);
    			 Drive.tankDrive(0, 0);
    			 Timer.delay(t);
    			 Drive.tankDrive(.25, -.25);//6, turn 90* to tote
    			 Timer.delay(.5);
    			 Drive.tankDrive(0, 0);
    			 Timer.delay(t);
    			 //*/
    			 /*
    			 relayArms.set(-.75);//7, 0pen arms
    			 Timer.delay(0.25);
    			 relayArms.set(.0);
    			 Timer.delay(t);
    			 armsLift.set(.15);//8, lower arms
    			 Timer.delay(0.25);
    			 armsLift.set(0);
    			 Timer.delay(t);
    			 relayArms.set(-.75);//9, close arm
    			 Timer.delay(0.25);
    			 relayArms.set(0);
    			 Timer.delay(t);
    			 armsLift.set(-.8);//910, lift arm
    			 Timer.delay(.25);
    			 armsLift.set(0);
    			 Timer.delay(t);
    			 Drive.tankDrive(-.25, .25);//10, turn 90*
    			 Timer.delay(.5);
    			 Drive.tankDrive(0, 0);
    			 Timer.delay(t);
    			 */
    			 /*
    			 Drive.tankDrive(.6, .6);//11, passed the platform
    			 Timer.delay(1);
    			 Drive.tankDrive(0, 0);
    			 Timer.delay(t);
    			 Drive.tankDrive(.25, -.25);//12, turn to goal
    			 Timer.delay(.5);
    			 Drive.tankDrive(0, 0);
    			 Timer.delay(t);
    			 Drive.tankDrive(.6, .6);//13, to the goal
    			 Timer.delay(1);
    			 Drive.tankDrive(0, 0);
    			 Timer.delay(t);
    			 armsLift.set(.15);//14, arms down to goal
    			 Timer.delay(0.25);
    			 armsLift.set(0);
    			 Timer.delay(t);
    			 relayArms.set(-.75);//15, arms open
    			 Timer.delay(0.1);
    			 relayArms.set(.0);
    			 */
    			 //while(autonomous)
    			 {
    			 }
    			 
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
        	
        	if (buttonClose && limit2) 		relayArms.set(-.75);//arms closing

        	else if (buttonOpen && limit1)  relayArms.set(.75);//arms opening
        	
        	else 							relayArms.set(0);
        	
        	if (armsRaise < .20 && armsLower < .2)    armsLift.set(-.15);
        	
        	else if (armsRaise > .2 && armsLower < .2) armsLift.set(-1);
        	
        	else if (armsRaise < .2 && armsLower > .2) armsLift.set(.15);
        	
        	else System.out.println("You screwed up somewhere.");
        	
        	//Above commands tab-spaced for readability (hopefully).
        	Drive.arcadeDrive(-leftSide, -rightSide);
        	SmartDashboard.putNumber("Battery", power.getVoltage());
            Timer.delay(0.001);		//1ms delay for very fast updating (now watch our CPU cook an egg!)
            
      }
    }
}