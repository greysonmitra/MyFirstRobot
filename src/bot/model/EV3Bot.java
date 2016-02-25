package bot.model;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.robotics.chassis.*;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.*;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class EV3Bot
{
	private String botMessage;
	private MovePilot botPilot;
	private int xPosition;
	private int yPosition;
	private long waitTime;
	
	private EV3UltrasonicSensor distanceSensor;
	private EV3TouchSensor backTouch;
	private float[] ultrasonicSamples;
	private float[] touchSamples;
	
	
	public EV3Bot()
	{
		this.botMessage = "Greyson uses greysonBot";
		this.xPosition = 50;
		this.yPosition = 50;
		this.waitTime = 4000;
		
		distanceSensor = new EV3UltrasonicSensor(LocalEV3.get().getPort("S1"));
		backTouch = new EV3TouchSensor(LocalEV3.get().getPort("S2"));
		
		setupPilot();
		displayMessage();
	}
	
/*	Used to initialize a MovePilot object outside of the constructor. */
	private void setupPilot()
	{
		Wheel leftWheel = WheeledChassis.modelWheel(Motor.A, 43.3).offset(-72);
		Wheel rightWheel = WheeledChassis.modelWheel(Motor.B, 43.3).offset(-72);
		WheeledChassis chassis = new WheeledChassis(new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
		botPilot = new MovePilot(chassis);
	}
	
	public void driveRoom()
	{
		ultrasonicSamples = new float [distanceSensor.sampleSize()];
		distanceSensor.fetchSample(ultrasonicSamples, 0);
		if(ultrasonicSamples[0] < 8.5)
		{
			botPilot.travel(20.00);
		}
		else
		{
			botPilot.travel(234.00);
		}
		
		//call private helper method here
		displayMessage("driveRoom");
	}
	
	private void displayMessage()
	{
		LCD.drawString(botMessage, xPosition, yPosition);
		Delay.msDelay(waitTime);
	}
	//overload 
	private void displayMessage(String message)
	{
		LCD.drawString(message, xPosition, yPosition);
		Delay.msDelay(waitTime);
	}
}
