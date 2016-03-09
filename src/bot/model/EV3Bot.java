package bot.model;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
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
		//backTouch = new EV3TouchSensor(LocalEV3.get().getPort("S2"));
		
		setupPilot();
		displayMessage();
	}
	
/*	Used to initialize a MovePilot object outside of the constructor. */
	private void setupPilot()
	{
		Wheel leftWheel = WheeledChassis.modelWheel(Motor.A, 43.3).offset(-72);
		Wheel rightWheel = WheeledChassis.modelWheel(Motor.B, 43.3).offset(72);
		WheeledChassis chassis = new WheeledChassis(new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
		botPilot = new MovePilot(chassis);
	}
	
	
	
	
	public void driveRoom()
	{
		ultrasonicSamples = new float [distanceSensor.sampleSize()];
		
		
		if((ultrasonicSamples[0]) < 810)
		{
			driveFromNorth();
		}
		else if(ultrasonicSamples[0] > 810)
		{
			driveFromSouth();
		}
		
		distanceSensor.fetchSample(ultrasonicSamples, 0);
		
		displayMessage("driveRoom");
	}	
		//^^^^Inside of the method above, try writing two different other methods that do from the North door to south, and south to north. Use zach's measurements to write data members to use for conditions in
		//my loops. It should be so that the total distance ( which is the [0] thing) is greater than (>) the distance to the wall/edge of that section. Ex: [0] > 7 * 12 * 2.54
	
		
	private void driveFromNorth()
	{
			botPilot.travel(80);
			
			distanceSensor.fetchSample(ultrasonicSamples, 0);

			if(( ultrasonicSamples[0]) > (810 / 2))
			{
				botPilot.stop();
				botPilot.rotate(60);
				botPilot.travel(3510);
				botPilot.stop();
				botPilot.rotate(-60);
			}
			else if(ultrasonicSamples[0] > 5940)
			{
				botPilot.travel(5940);
				botPilot.rotate(60);
				botPilot.stop();
			}
			else if(ultrasonicSamples[0] > 4000 / 2)
			{
				botPilot.travel(10000);
				botPilot.stop();
			}
	}
	
	private void driveFromSouth()
	{
		botPilot.travel(10000);
		
		distanceSensor.fetchSample(ultrasonicSamples, 0);

		if((ultrasonicSamples[0] > 3780))
		{
			botPilot.stop();
			botPilot.rotate(-60);
			botPilot.travel(5940);
			botPilot.stop();
			botPilot.rotate(60);
		}
		else if(ultrasonicSamples[0] > 3510)
		{
			botPilot.travel(3510);
			botPilot.stop();
			botPilot.rotate(-60
					);
			botPilot.travel(10000);
			botPilot.stop();
		}
	}

	
		
/*		if(ultrasonicSamples[0] < 8.5)
		{
			botPilot.travel(20.00);
			botPilot.rotate(90);
		}
		else
		{
			botPilot.travel(234.00);
		}                                    */
		
		//call private helper method here
	
	
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
